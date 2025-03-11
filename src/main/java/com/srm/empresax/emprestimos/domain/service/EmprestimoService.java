package com.srm.empresax.emprestimos.domain.service;

import com.srm.empresax.emprestimos.domain.model.Emprestimo;
import com.srm.empresax.emprestimos.domain.model.Pessoa;
import com.srm.empresax.emprestimos.domain.model.StatusPagamento;
import com.srm.empresax.emprestimos.domain.model.TipoPessoa;
import com.srm.empresax.emprestimos.domain.repository.EmprestimoRepository;
import com.srm.empresax.emprestimos.domain.repository.PessoaRepository;
import com.srm.empresax.emprestimos.dto.EmprestimoDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final PessoaRepository pessoaRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, PessoaRepository pessoaRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional
    public Emprestimo criarEmprestimo(EmprestimoDto emprestimoDto) {
        Pessoa pessoa = pessoaRepository.findById(emprestimoDto.getPessoaId())
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada"));

        // Valida CPF e CNPJ
        validarCpfCnpj(pessoa.getIdentificador());

        // Valida regras específicas de tipo de pessoa
        if (pessoa.getTipoPessoa() == TipoPessoa.ESTUDANTE_UNIVERSITARIO) {
            validarEstudanteUniversitario(pessoa.getIdentificador());
        } else if (pessoa.getTipoPessoa() == TipoPessoa.APOSENTADO) {
            validarAposentado(pessoa.getIdentificador());
        }

        // Valida limites financeiros
        validarLimitesFinanceiros(pessoa, emprestimoDto.getValor(), emprestimoDto.getNumeroParcelas());

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setPessoa(pessoa);
        emprestimo.setValor(emprestimoDto.getValor());
        emprestimo.setNumeroParcelas(emprestimoDto.getNumeroParcelas());
        emprestimo.setStatusPagamento(StatusPagamento.PENDENTE);

        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo buscarPorId(Long id) {
        return emprestimoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empréstimo não encontrado"));
    }

    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

    @Transactional
    public void excluir(Long id) {
        if (!emprestimoRepository.existsById(id)) {
            throw new IllegalArgumentException("Empréstimo não encontrado");
        }
        emprestimoRepository.deleteById(id);
    }

    // ============================
    // MÉTODOS AUXILIARES DE VALIDAÇÃO
    // ============================

    private void validarCpfCnpj(String identificador) {
        String cpfRegex = "^[0-9]{11}$";
        String cnpjRegex = "^[0-9]{14}$";

        if (!Pattern.matches(cpfRegex, identificador) && !Pattern.matches(cnpjRegex, identificador)) {
            throw new IllegalArgumentException("O identificador deve ser um CPF ou CNPJ válido.");
        }
    }

    private void validarEstudanteUniversitario(String identificador) {
        if (identificador.length() != 8) {
            throw new IllegalArgumentException("Identificador de estudante universitário deve ter 8 caracteres.");
        }

        int primeiro = Character.getNumericValue(identificador.charAt(0));
        int ultimo = Character.getNumericValue(identificador.charAt(7));

        if ((primeiro + ultimo) != 9) {
            throw new IllegalArgumentException("A soma do primeiro e último dígito do identificador deve ser 9.");
        }
    }

    private void validarAposentado(String identificador) {
        if (identificador.length() != 10) {
            throw new IllegalArgumentException("Identificador de aposentado deve ter 10 caracteres.");
        }

        char ultimo = identificador.charAt(9);
        for (int i = 0; i < 9; i++) {
            if (identificador.charAt(i) == ultimo) {
                throw new IllegalArgumentException("O último dígito do identificador não pode estar presente nos outros 9 dígitos.");
            }
        }
    }
    private void validarLimitesFinanceiros(Pessoa pessoa, BigDecimal valorEmprestimo, int numeroParcelas) {
        if (valorEmprestimo.compareTo(pessoa.getValorMaximoEmprestimo()) > 0) {
            throw new IllegalArgumentException("Valor do empréstimo ultrapassa o limite máximo permitido para o tipo de pessoa.");
        }

        if (numeroParcelas > 24) {
            throw new IllegalArgumentException("O número de parcelas não pode ser maior que 24.");
        }

        BigDecimal valorParcela = valorEmprestimo.divide(BigDecimal.valueOf(numeroParcelas), 2, RoundingMode.HALF_UP);
        if (valorParcela.compareTo(pessoa.getValorMinimoParcela()) < 0) {
            throw new IllegalArgumentException("O valor de cada parcela é inferior ao mínimo permitido para o tipo de pessoa.");
        }
    }
}


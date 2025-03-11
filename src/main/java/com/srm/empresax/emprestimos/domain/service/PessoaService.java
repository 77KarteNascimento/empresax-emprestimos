package com.srm.empresax.emprestimos.domain.service;

import com.srm.empresax.emprestimos.domain.model.Pessoa;
import com.srm.empresax.emprestimos.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;



    // Lógica de definição do tipo e valores com base no identificador
    private void definirParametros(Pessoa pessoa) {
        String identificador = pessoa.getIdentificador();
        int tamanho = identificador != null ? identificador.length() : 0;

        if (tamanho == 11) {
            pessoa.setTipoIdentificador("PF");
            pessoa.setValorMinimoParcela(BigDecimal.valueOf(300.00));
            pessoa.setValorMaximoEmprestimo(BigDecimal.valueOf(10000.00));
        } else if (tamanho == 14) {
            pessoa.setTipoIdentificador("PJ");
            pessoa.setValorMinimoParcela(BigDecimal.valueOf(1000.00));
            pessoa.setValorMaximoEmprestimo(BigDecimal.valueOf(100000.00));
        } else if (tamanho == 8) {
            pessoa.setTipoIdentificador("EU");
            pessoa.setValorMinimoParcela(BigDecimal.valueOf(100.00));
            pessoa.setValorMaximoEmprestimo(BigDecimal.valueOf(10000.00));
        } else if (tamanho == 10) {
            pessoa.setTipoIdentificador("AP");
            pessoa.setValorMinimoParcela(BigDecimal.valueOf(400.00));
            pessoa.setValorMaximoEmprestimo(BigDecimal.valueOf(25000.00));
        } else {
            throw new IllegalArgumentException("Identificador inválido: tamanho não reconhecido.");
        }
    }

    public Pessoa criarPessoa(Pessoa pessoa) {
        definirParametros(pessoa);
        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> buscarPessoaPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    public Pessoa atualizarPessoa(Long id, Pessoa pessoaAtualizada) {
        return pessoaRepository.findById(id).map(pessoa -> {
            pessoa.setNome(pessoaAtualizada.getNome());
            pessoa.setDataNascimento(pessoaAtualizada.getDataNascimento());
            pessoa.setIdentificador(pessoaAtualizada.getIdentificador());
            // Recalcula os parâmetros com base no novo identificador
            definirParametros(pessoa);
            return pessoaRepository.save(pessoa);
        }).orElseThrow(() -> new RuntimeException("Pessoa não encontrada com id " + id));
    }

    public void deletarPessoa(Long id) {
        pessoaRepository.deleteById(id);
    }
}


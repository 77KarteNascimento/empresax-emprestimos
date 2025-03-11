package com.srm.empresax.emprestimos.domain.service;

import com.srm.empresax.emprestimos.domain.model.Emprestimo;
import com.srm.empresax.emprestimos.domain.model.Pessoa;
import com.srm.empresax.emprestimos.domain.model.StatusPagamento;
import com.srm.empresax.emprestimos.domain.repository.EmprestimoRepository;
import com.srm.empresax.emprestimos.domain.repository.PessoaRepository;
import com.srm.empresax.emprestimos.dto.EmprestimoDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        Optional<Pessoa> pessoaOpt = pessoaRepository.findById(emprestimoDto.getPessoaId());
        if (pessoaOpt.isEmpty()) {
            throw new IllegalArgumentException("Pessoa não encontrada");
        }

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setPessoa(pessoaOpt.get());
        emprestimo.setValor(emprestimoDto.getValor());
        emprestimo.setNumeroParcelas(emprestimoDto.getNumeroParcelas());

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
}

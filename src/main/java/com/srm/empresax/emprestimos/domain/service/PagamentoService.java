package com.srm.empresax.emprestimos.domain.service;

import com.srm.empresax.emprestimos.domain.model.Emprestimo;
import com.srm.empresax.emprestimos.domain.model.StatusPagamento;
import com.srm.empresax.emprestimos.domain.repository.EmprestimoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PagamentoService {

    private final EmprestimoRepository emprestimoRepository;

    public PagamentoService(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }

    @Transactional
    public void pagarEmprestimo(Long idEmprestimo) {
        Emprestimo emprestimo = emprestimoRepository.findById(idEmprestimo)
                .orElseThrow(() -> new IllegalArgumentException("Empréstimo não encontrado com ID: " + idEmprestimo));

        if (emprestimo.getStatusPagamento() == StatusPagamento.PAGO) {
            throw new IllegalStateException("Este empréstimo já está pago.");
        }

        emprestimo.setStatusPagamento(StatusPagamento.PAGO);
        emprestimoRepository.save(emprestimo);
    }
}
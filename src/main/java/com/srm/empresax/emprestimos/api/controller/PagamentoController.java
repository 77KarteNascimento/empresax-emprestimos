package com.srm.empresax.emprestimos.api.controller;

import com.srm.empresax.emprestimos.domain.service.PagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping("/{idEmprestimo}")
    public ResponseEntity<String> pagarEmprestimo(@PathVariable Long idEmprestimo) {
        pagamentoService.pagarEmprestimo(idEmprestimo);
        return ResponseEntity.ok("Pagamento realizado com sucesso.");
    }
}

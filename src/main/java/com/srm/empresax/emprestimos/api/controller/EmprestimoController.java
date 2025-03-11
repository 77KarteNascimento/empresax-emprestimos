package com.srm.empresax.emprestimos.api.controller;

import com.srm.empresax.emprestimos.domain.model.Emprestimo;
import com.srm.empresax.emprestimos.dto.EmprestimoDto;
import com.srm.empresax.emprestimos.domain.service.EmprestimoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {


        private final EmprestimoService emprestimoService;

        public EmprestimoController(EmprestimoService emprestimoService) {
            this.emprestimoService = emprestimoService;
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public ResponseEntity<Emprestimo> criarEmprestimo(@Valid @RequestBody EmprestimoDto emprestimoDto) {
            Emprestimo emprestimo = emprestimoService.criarEmprestimo(emprestimoDto);
            return ResponseEntity.ok(emprestimo);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Emprestimo> buscarPorId(@PathVariable Long id) {
            Emprestimo emprestimo = emprestimoService.buscarPorId(id);
            return ResponseEntity.ok(emprestimo);
        }

        @GetMapping
        public ResponseEntity<List<Emprestimo>> listarTodos() {
            List<Emprestimo> emprestimos = emprestimoService.listarTodos();
            return ResponseEntity.ok(emprestimos);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> excluir(@PathVariable Long id) {
            emprestimoService.excluir(id);
            return ResponseEntity.noContent().build();
        }
    }



package com.srm.empresax.emprestimos.domain.model;



import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    @NotNull(message = "O valor do empréstimo é obrigatório")
    @Min(value = 100, message = "O valor mínimo do empréstimo deve ser R$ 100,00")
    private BigDecimal valor;

    @NotNull(message = "O número de parcelas é obrigatório")
    @Min(value = 1, message = "O número mínimo de parcelas deve ser 1")
    private Integer numeroParcelas;

    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

    private LocalDateTime dataCriacao;

    public Emprestimo() {
        this.dataCriacao = LocalDateTime.now();
        this.statusPagamento = StatusPagamento.PENDENTE;
    }

    //getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }


}



package com.srm.empresax.emprestimos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public class EmprestimoDto {

    @NotNull(message = "O ID da pessoa é obrigatório")
    private Long pessoaId;

    @NotNull(message = "O valor do empréstimo é obrigatório")
    @Min(value = 100, message = "O valor mínimo do empréstimo deve ser R$ 100,00")
    private BigDecimal valor;

    @NotNull(message = "O número de parcelas é obrigatório")
    @Min(value = 1, message = "O número mínimo de parcelas deve ser 1")
    private Integer numeroParcelas;

    public EmprestimoDto() {
    }

    //getters e setters
    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
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
}
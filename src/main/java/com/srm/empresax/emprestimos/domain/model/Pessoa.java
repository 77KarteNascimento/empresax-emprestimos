package com.srm.empresax.emprestimos.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoPessoa tipoPessoa;

    @NotBlank(message = "Identificador é obrigatório")
    private String identificador;

    private LocalDate dataNascimento;

    // Este campo será definido automaticamente com base no tamanho do identificador
    private String tipoIdentificador;

    @Column(precision = 18, scale = 2) // Definição adequada para valores monetários
    private BigDecimal valorMinimoParcela;

    @Column(precision = 18, scale = 2)
    private BigDecimal valorMaximoEmprestimo;
    // Getters e setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getIdentificador() {
        return identificador;
    }
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getTipoIdentificador() {
        return tipoIdentificador;
    }
    public void setTipoIdentificador(String tipoIdentificador) {
        this.tipoIdentificador = tipoIdentificador;
    }

    public BigDecimal getValorMinimoParcela() {
        return valorMinimoParcela;
    }

    public void setValorMinimoParcela(BigDecimal valorMinimoParcela) {
        this.valorMinimoParcela = valorMinimoParcela;
    }

    public BigDecimal getValorMaximoEmprestimo() {
        return valorMaximoEmprestimo;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public void setValorMaximoEmprestimo(BigDecimal valorMaximoEmprestimo) {
        this.valorMaximoEmprestimo = valorMaximoEmprestimo;
    }
}
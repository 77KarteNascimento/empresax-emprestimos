package com.srm.empresax.emprestimos.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Identificador é obrigatório")
    private String identificador;

    private LocalDate dataNascimento;

    // Este campo será definido automaticamente com base no tamanho do identificador
    private String tipoIdentificador;

    // Valores definidos automaticamente
    private Double valorMinimoParcela;
    private Double valorMaximoEmprestimo;

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
    public Double getValorMinimoParcela() {
        return valorMinimoParcela;
    }
    public void setValorMinimoParcela(Double valorMinimoParcela) {
        this.valorMinimoParcela = valorMinimoParcela;
    }
    public Double getValorMaximoEmprestimo() {
        return valorMaximoEmprestimo;
    }
    public void setValorMaximoEmprestimo(Double valorMaximoEmprestimo) {
        this.valorMaximoEmprestimo = valorMaximoEmprestimo;
    }
}
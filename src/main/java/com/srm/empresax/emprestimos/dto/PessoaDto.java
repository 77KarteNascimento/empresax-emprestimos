package com.srm.empresax.emprestimos.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class PessoaDto {

    @NotBlank(message = "{NotBlank}")
    private String nome;

    @NotBlank(message = "{NotBlank}")
    @Size(min = 8, max = 14, message = "{Size}")
    private String identificador;

    @NotNull(message = "{NotNull}")
    private LocalDate dataNascimento;

    @NotBlank(message = "{NotBlank}")
    private String tipoIdentificador;

    @Min(value = 100, message = "{Min}")
    private Double valorMinimoParcelas;

    @Max(value = 100000, message = "{Max}")
    private Double valorMaximoEmprestimo;



    //getters e setters
    public @NotBlank(message = "{NotBlank}") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "{NotBlank}") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "{NotBlank}") @Size(min = 8, max = 14, message = "{Size}") String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(@NotBlank(message = "{NotBlank}") @Size(min = 8, max = 14, message = "{Size}") String identificador) {
        this.identificador = identificador;
    }

    public @NotNull(message = "{NotNull}") LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(@NotNull(message = "{NotNull}") LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public @NotBlank(message = "{NotBlank}") String getTipoIdentificador() {
        return tipoIdentificador;
    }

    public void setTipoIdentificador(@NotBlank(message = "{NotBlank}") String tipoIdentificador) {
        this.tipoIdentificador = tipoIdentificador;
    }

    public @Min(value = 100, message = "{Min}") Double getValorMinimoParcelas() {
        return valorMinimoParcelas;
    }

    public void setValorMinimoParcelas(@Min(value = 100, message = "{Min}") Double valorMinimoParcelas) {
        this.valorMinimoParcelas = valorMinimoParcelas;
    }

    public @Max(value = 100000, message = "{Max}") Double getValorMaximoEmprestimo() {
        return valorMaximoEmprestimo;
    }

    public void setValorMaximoEmprestimo(@Max(value = 100000, message = "{Max}") Double valorMaximoEmprestimo) {
        this.valorMaximoEmprestimo = valorMaximoEmprestimo;
    }
}

package com.srm.empresax.emprestimos.api.exceptionhandler;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public class Problema {


    @NotNull(message = "A data e hora são obrigatórias")
    private LocalDateTime dataHora;

    @NotBlank(message = "A mensagem é obrigatória")
    private String mensagem;

    private Integer status;

    private String detalhes;


    public Problema() {
    }

    public Problema(LocalDateTime dataHora, String mensagem, Integer status, String detalhes) {
        this.dataHora = dataHora;
        this.mensagem = mensagem;
        this.status = status;
        this.detalhes = detalhes;
    }

    //getters e setters
    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    // Padrão Builder (implementação manual)
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private LocalDateTime dataHora;
        private String mensagem;
        private Integer status;
        private String detalhes;

        public Builder dataHora(LocalDateTime dataHora) {
            this.dataHora = dataHora;
            return this;
        }

        public Builder mensagem(String mensagem) {
            this.mensagem = mensagem;
            return this;
        }

        public Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public Builder detalhes(String detalhes) {
            this.detalhes = detalhes;
            return this;
        }

        public Problema build() {
            return new Problema(dataHora, mensagem, status, detalhes);
        }
    }

}

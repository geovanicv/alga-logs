package com.geovani.algaweek.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

public class ExceptionMessage {

    private Integer status;
    private LocalDateTime dataHora;
    private String titulo;

    private List<ExceptionError> exceptions;

    public ExceptionMessage() {}
    public ExceptionMessage(Integer status, LocalDateTime dataHora, String titulo) {
        this.status = status;
        this.dataHora = dataHora;
        this.titulo = titulo;
    }

    public static class ExceptionError {
        private String nome;
        private String mensagem;

        public ExceptionError(String nome, String mensagem) {
            super();
            this.nome = nome;
            this.mensagem = mensagem;
        }
        public String getNome() {
            return nome;
        }
        public void setNome(String nome) {
            this.nome = nome;
        }
        public String getMensagem() {
            return mensagem;
        }
        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }


    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<ExceptionError> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<ExceptionError> exceptions) {
        this.exceptions = exceptions;
    }
}


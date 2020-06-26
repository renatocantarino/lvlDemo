package com.cantarino.application.demo.Dtos;

import javax.validation.constraints.NotBlank;

public class EstadoDto {

    private String nome;

    @NotBlank(message = "Sigla obrigatoria")
    private String Sigla;

    public EstadoDto(String nome, String sigla) {
        this.nome = nome;
        Sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return Sigla;
    }

    public void setSigla(String sigla) {
        Sigla = sigla;
    }
}

package com.cantarino.application.demo.Dtos;


import com.cantarino.application.demo.Models.Cidade;
import com.cantarino.application.demo.Models.Estado;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

public class CidadeDto {

    @NotBlank(message = "nome obrigatorio")
    private String nome;

    @NotBlank(message = "Sigla obrigatoria")
    private String siglaEstado;

    public CidadeDto(String nome, String siglaEstado) {
        this.nome = nome;
        this.siglaEstado = siglaEstado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return siglaEstado;
    }

    public void setSigla(String sigla) {
        this.siglaEstado = sigla;
    }

    public static List<CidadeDto> Transform(List<Cidade> list) {

        return list.stream()
                .map(cidade -> new CidadeDto(cidade.getNome(),
                                cidade.getEstado().getSigla())).collect(Collectors.toList());

    }

}

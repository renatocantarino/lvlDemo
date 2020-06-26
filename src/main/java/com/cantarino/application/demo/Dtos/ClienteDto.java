package com.cantarino.application.demo.Dtos;

import com.cantarino.application.demo.Models.Cidade;
import com.cantarino.application.demo.Models.Cliente;
import com.cantarino.application.demo.Models.Enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;

import static org.hibernate.type.descriptor.java.DateTypeDescriptor.DATE_FORMAT;

public class ClienteDto {


    @NotBlank(message = "nome obrigatorio")
    private String nomeCompleto;

    @NotBlank(message = "cidade obrigatorio")
    private String cidade;

    private Sexo sexo;

    @NotBlank(message = "data Nascimento obrigatorio")
    @JsonFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(dataType = "java.sql.Date" , example = "2016-01-01")
    private LocalDate dataNascimento;

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public static Cliente transform(ClienteDto dto)
    {
        Cliente cliente = new Cliente(dto.getNomeCompleto(), dto.getSexo() , dto.getDataNascimento());
        Cidade cidade = new Cidade(dto.getCidade());
        cliente.setCidade(cidade);

        return cliente;
    }
}

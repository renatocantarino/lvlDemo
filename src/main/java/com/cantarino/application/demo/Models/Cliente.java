package com.cantarino.application.demo.Models;

import com.cantarino.application.demo.Models.Enums.Sexo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Table(name="Cliente")
@Entity
public class Cliente implements Serializable {

    private static final long serialVersionUID = -6380749575516426900L;



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id",updatable = false)
    private Long id;

    @Column(name="Nome")
    private String nomeCompleto;

    @Enumerated(EnumType.STRING)
    @Column(name = "Sexo")
    private Sexo tipoPessoa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Codigo_cidade")
    @JsonIgnore
    private Cidade cidade;

    @Column(name="dataNascimento")
    private LocalDate dataNascimento;

    @Column(name="idade")
    private int idade;

    public Cliente()
    {}

    public Cliente( String nomeCompleto , Sexo tipoPessoa ,  LocalDate dataNascimento) {

        this.nomeCompleto = nomeCompleto;
        this.tipoPessoa = tipoPessoa;
        this.dataNascimento = dataNascimento;
    }

    @PrePersist
    @PreUpdate
    private void beforePersist() {
        int hoje = LocalDateTime.now().getYear();
        int diff = hoje - dataNascimento.getYear();

        idade = diff;
    }





    public Long getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Sexo getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(Sexo tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        return id != null ? id.equals(cliente.id) : cliente.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

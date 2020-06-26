package com.cantarino.application.demo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="Cidade")
@Entity
public class Cidade implements Serializable {

    private static final long serialVersionUID = -6380749575516426900L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id",updatable = false)
    private Long id;

    @Column(name="Nome")
    private String nome;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_estado")
    @JsonIgnore
    private Estado estado;

    public Cidade(){}

    public Cidade(String nome , Estado estado)
    {
        this.setNome(nome);
        this.setEstado(estado);
    }

    public Cidade(String nome)
    {
        this.setNome(nome);

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cidade cidade = (Cidade) o;

        return id.equals(cidade.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

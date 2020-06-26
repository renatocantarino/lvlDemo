package com.cantarino.application.demo.Models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Table(name="Estado")
@Entity
public class Estado implements Serializable {

    private static final long serialVersionUID = -6380749575516426900L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id",updatable = false)
    private Long id;


    @Column(name="Sigla")
    @Size(min = 2 , max = 2, message = "A Sigla deve ter 2 caracteres")
    private String sigla;

    @Column(name="Nome")
    private String nome;

    public Estado(){}

    public Estado(String nome , String sigla) {
        this.setSigla(sigla);
        this.setNome(nome);
    }

    public Estado(String sigla) {
        this.setSigla(sigla);
    }

    @PrePersist
    @PreUpdate
    private void beforePersist() {
        sigla = sigla.trim().toUpperCase();
        nome = nome.trim().toUpperCase();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getSigla() {
        return sigla;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Estado estado = (Estado) o;

        return id.equals(estado.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }



}

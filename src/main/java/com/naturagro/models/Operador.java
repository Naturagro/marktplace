package com.naturagro.models;


import javax.persistence.*;
import java.util.Objects;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Operador extends Funcionario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;

    public Operador() {}

    public Operador(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = Cargo.Operador;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Operador operador = (Operador) o;
        return Objects.equals(id, operador.id);
    }
}

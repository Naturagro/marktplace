package com.naturagro.models;


import javax.persistence.*;


@Entity
public class Funcionario {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cpf;
    private String nome;
    private String senha;
    @Enumerated(EnumType.STRING)
    Cargo cargo;

    public Funcionario() {}

    public Funcionario(String nome, String cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }

    public Funcionario gerente(String nome, String cpf, String senha) {
        Funcionario f = new Funcionario(nome, cpf, senha);
        f.setCargo(Cargo.GERENTE);
        return f;
    }

    public Funcionario operador(String nome, String cpf, String senha) {
        Funcionario o = new Funcionario(nome, cpf, senha);
        o.setCargo(Cargo.OPERADOR);
        return o;
    }

    public Funcionario estoquista(String nome, String cpf, String senha) {
        Funcionario e = new Funcionario(nome, cpf, senha);
        e.setCargo(Cargo.ESTOQUISTA);
        return e;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

}

package com.naturagro.models;

//criando a classe que guarda as informações de login
public class Cadastro {
    private long id;
    private String nomeusuario;
    private String senha;
    private String confirmacaoSenha;

    public Cadastro(String nomeusuario, String senha, String confirmacaoSenha) {
        this.nomeusuario = nomeusuario;
        this.senha = senha;
        this.confirmacaoSenha = confirmacaoSenha;
    }
}



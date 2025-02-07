package com.naturagro.models;


public class FuncionarioFactory {
    private static Funcionario funcionario = new Funcionario();

    public Funcionario criarGerente(String nome, String cpf, String senha) {
        return funcionario.gerente(nome, cpf, senha);
    }

    public Funcionario criarOperador(String nome, String cpf, String senha) {
        return funcionario.operador(nome, cpf, senha);
    }

    public Funcionario criarEstoquista(String nome, String cpf, String senha) {
        return funcionario.estoquista(nome, cpf, senha);
    }
}

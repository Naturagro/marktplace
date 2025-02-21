package com.naturagro.service;

import com.naturagro.DAO.DAO;
import com.naturagro.models.Funcionario;

import java.util.List;
public class FuncionarioService extends DAO<Funcionario> {

    public FuncionarioService() {
        super(Funcionario.class);
    }

    // Busca um funcionário pelo CPF utilizando a query nomeada
    public Funcionario buscarPorCpf(String cpf) {
        return consultarUm("Funcionario.buscarPorCpf", "cpf", cpf);
    }

    // Busca funcionários pelo nome (parcial), utilizando a query nomeada
    public List<Funcionario> buscarPorNome(String nome) {
        return consultar("Funcionario.buscarPorNome", "nome", "%" + nome + "%");
    }

    // Retorna todos os funcionários utilizando a query nomeada
    public List<Funcionario> buscarTodos() {
        return consultar("Funcionario.buscarTodos");
    }

    public List<Funcionario> consultarVendas(Long id) {
        return consultar("Funcionario.vendas", id);
    }

    public void adicionarFuncionarioBanco(Funcionario funcionario){
        abrirT();
        incluir(funcionario);
        fecharT();
        fechar();
    }
}

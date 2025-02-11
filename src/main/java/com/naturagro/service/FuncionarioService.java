package com.naturagro.service;

import com.naturagro.DAO.DAO;
import com.naturagro.models.Funcionario;

import java.util.List;
public class FuncionarioService extends DAO<Funcionario> {

    public FuncionarioService() {
        super(Funcionario.class);
    }

    // Salva um funcionário no banco de dados
    public void salvar(Funcionario funcionario) {
        incluirAtomico(funcionario);
    }

    // Busca um funcionário pelo ID
    public Funcionario buscarPorId(Long id) {
        return obterPorID(id);
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

    // Atualiza um funcionário existente
    public void atualizar(Funcionario funcionario) {
        abrirT();
        mesclar(funcionario);
        fecharT();
    }

    // Remove um funcionário do banco de dados
    public void remover(Funcionario funcionario) {
        super.remover(funcionario);
    }


}

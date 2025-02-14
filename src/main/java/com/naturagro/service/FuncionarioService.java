package com.naturagro.service;

import com.naturagro.DAO.DAO;
import com.naturagro.models.Funcionario;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

//todo: alterar a tabela automaticamente pra o id ser autoincrement
@Table(name = "funcionarios") //tabela genérica de teste
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


    /*
    Para atualizar o funcionario
    chamar o metodo mesclar()

    Para deletar funcionario só chamar
    remover()
    */



    public void adicionarFuncionarioBanco(Funcionario funcionario){
        abrirT();
        incluir(funcionario);
        fecharT();
        fechar();
    }

}

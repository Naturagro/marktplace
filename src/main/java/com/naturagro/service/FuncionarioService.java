package com.naturagro.service;

import com.naturagro.DAO.DAO;
import com.naturagro.models.Funcionario;

import java.util.List;
public class FuncionarioService extends DAO<Funcionario> {

    /*
    A classe funcionário herda todos os metodos de DAO logo é contraproducente
    recriar métodos que já existem na outra classe


    Essa classe não pode ser uma tabela no banco de dados, não foi feita para isso
    Nossa entidade está localizada no pacote models
    Lá constam todas as configurações necessárias, além de que o ID, por padrão da nossa aplicação
    já foi setado como auto incremetal, quaisquer tester que preciser ser realizados com a criação de funcionários
    podem ser feitos através da classe FuncionarioFactory e persistidos no banco e na tabela respectiva
    com FuncionarioService através do metodo incluir() ou incluirAtomico()

    As únicas adicções que precisa ser feitas nesta classe são adições de métodos de pesquisa
    favor mantê-la inalterada
     */
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

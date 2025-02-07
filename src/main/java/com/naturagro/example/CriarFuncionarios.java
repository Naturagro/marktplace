package com.naturagro.example;

import com.naturagro.models.Funcionario;
import com.naturagro.models.FuncionarioFactory;
import com.naturagro.service.FuncionarioService;

import java.util.ArrayList;
import java.util.List;

public class CriarFuncionarios {
    public static void main(String[] args) {

        /*
        Esse seria um método mais tradicional para os funcionários por seus tipos(gerente, operador, estoquista)
        dentro da classe Funcionário
        contudo, para evitar erros, melhor será fazer usando a Factory
         */
        Funcionario funcionario = new Funcionario();
        //Funcionario f = funcionario.gerente("Victor G", "08272635461", "123456");
        FuncionarioService funcionarioService = new FuncionarioService();
        //funcionarioService.incluirAtomico(f);

        /*
        Aqui instanciamos um objeto que será responsável por construir cada Funcionario
        O FuncionarioFactory
        Cada método dele é responsável por retornar um funcionário específico
        Depois é só chamar o funcionarioService.incluirAtomico() com o funcionário como parâmetros
        para ser persistido no bacno de dados

        */
        FuncionarioFactory funcionarioFactory = new FuncionarioFactory();
        Funcionario o = funcionarioFactory.criarOperador("Carlos Batista", "00000000", "abacaxi");

        Funcionario e = funcionarioFactory.criarEstoquista("Júlio Iglesias", "2130278", "patrickestrela");

        Funcionario g = funcionarioFactory.criarGerente("Sr Sirigueijo","221354687", "perola");

        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(o);
        funcionarios.add(e);
        funcionarios.add(g);
        for (Funcionario f : funcionarios) {
            funcionarioService.incluirAtomico(f);
        }
        /*
        Descomente a chamada dos métodos de funcionarioService para adicioná-los ao banco
         */
    }
}

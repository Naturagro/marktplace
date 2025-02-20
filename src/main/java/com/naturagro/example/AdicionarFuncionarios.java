package com.naturagro.example;

import com.naturagro.models.Funcionario;
import com.naturagro.models.FuncionarioFactory;
import com.naturagro.service.FuncionarioService;

import java.util.ArrayList;
import java.util.List;

public class AdicionarFuncionarios {
    public void adicionarF() {

        /*
        Esse seria um metodo mais tradicional para os funcionários por seus tipos(gerente, operador, estoquista)
        dentro da classe Funcionário
        contudo, para evitar erros, melhor será fazer usando a Factory

        Funcionario funcionario = new Funcionario();
        //Funcionario f = funcionario.gerente("Victor G", "08272635461", "123456");
        FuncionarioService funcionarioService = new FuncionarioService();
        //funcionarioService.incluirAtomico(f);


        Aqui instanciamos um objeto que será responsável por construir cada Funcionario
        O FuncionarioFactory
        Cada metodo dele é responsável por retornar um funcionário específico
        Depois é só chamar o funcionarioService.incluirAtomico() com o funcionário como parâmetros
        para ser persistido no bacno de dados

        */
        FuncionarioService funcionarioService = new FuncionarioService();
        FuncionarioFactory funcionarioFactory = new FuncionarioFactory();

        Funcionario o = funcionarioFactory.criarOperador("Carlos Batista", "00000000", "abacaxi");
        Funcionario e = funcionarioFactory.criarEstoquista("Júlio Iglesias", "2130278", "patrickestrela");
        Funcionario g = funcionarioFactory.criarGerente("Sr Sirigueijo","221354687", "perola");
        Funcionario v = funcionarioFactory.criarGerente("Victor","08272635461", "123456");
        Funcionario zero = funcionarioFactory.criarGerente("Zero", "0", "0");
        Funcionario admin = funcionarioFactory.criarGerente("admin", "admin", "admin");
        Funcionario Pedro = funcionarioFactory.criarGerente("Pedro", "15360004428","123456");
        Funcionario Chico = funcionarioFactory.criarGerente("Chico", "12670092411", "3433");

        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(o);
        funcionarios.add(e);
        funcionarios.add(g);
        funcionarios.add(v);
        funcionarios.add(zero);
        funcionarios.add(admin);
        funcionarios.add(Pedro);
        funcionarios.add(Chico);
        for (Funcionario f : funcionarios) {
            funcionarioService.incluirAtomico(f);
        }

        /*
        Descomente a chamada dos métodos de funcionarioService para adicioná-los ao banco
         */
    }
}

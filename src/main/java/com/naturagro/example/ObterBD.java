package com.naturagro.example;

import com.naturagro.models.Funcionario;
import com.naturagro.service.FuncionarioService;

import java.util.List;

public class ObterBD {
    public static void main(String[] args) {
        FuncionarioService funcionarioService = new FuncionarioService();
        List<Funcionario> funcionarios = funcionarioService.obterTodos(10,0);

        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.getNome());
            System.out.println(funcionario.getCargo());
            System.out.println();
        }

    }
}

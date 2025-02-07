package com.naturagro.controllers;

import com.naturagro.service.FuncionarioService;
import com.naturagro.models.Funcionario;

//lança a exceção de cadastro
public class AccessControlController {

    private FuncionarioService funcionarioService;

    public AccessControlController() {
        this.funcionarioService = funcionarioService;
    }

    public void registerUser(String userName, String password, String passwordConfirmation) throws ControlException {
        //verificações dos campos
        if (userName == null || userName.isBlank()) {
            throw new ControlException("O nome do usuário é obrigatório!");
        }
        if (password == null || passwordConfirmation == null) {
            throw new ControlException("A senha e a confirmação são obrigatórias!");
        }
        if (password.isBlank()|| passwordConfirmation.isBlank()) {
            throw new ControlException("A senha e a confirmação não podem estar vazias!");
        }
        if (password.length() < 6) {
            throw new ControlException("A senha tem que ter no mínimo 6 caracteres!");
        }
        if (!password.equals(passwordConfirmation)) {
            throw new ControlException("As senhas não coincidem!");
        }
        /*


        Funcionario funcionarioExistente = funcionarioService.obterPorID(userName); // Usando userName como ID

        if (funcionarioExistente != null) {
            throw new ControlException("O usuário já existe!");
            */
        }

    }



    /*
        Para verificar a existência de um usuário no sistema
        só é necessário chamar o método obterPorId() da classe
        FuncionarioService e passar o id como parâmetro

        se o retorno for != null o objeto existe no bd, se for == null é só persisti-lo com incluirAtomico()
    */


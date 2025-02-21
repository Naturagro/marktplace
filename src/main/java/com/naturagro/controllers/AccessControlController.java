package com.naturagro.controllers;

import com.naturagro.service.FuncionarioService;
import com.naturagro.utils.ValidadorCPF;

//lança a exceção de cadastro
public class AccessControlController {

    private FuncionarioService funcionarioService;

    public AccessControlController() {
        this.funcionarioService = funcionarioService;
    }

    public void registerUser(String userName, String cpf, String password, String passwordConfirmation) throws ControlException {
        //verificações dos campos
        if (userName == null || userName.isBlank()) {
            throw new ControlException("O nome do usuário é obrigatório!");
        }

        if (cpf == null || cpf.isBlank()){
            throw new ControlException("O CPF é obrigatório!");
        }

        ValidadorCPF validarCpf = new ValidadorCPF();
        validarCpf.validarCPF(cpf);
        if (!(validarCpf.validarCPF(cpf))){
            throw new ControlException("CPF inválido!");
        }

        if (password == null || passwordConfirmation == null) {
            throw new ControlException("A senha e a confirmação são obrigatórias!");
        }
        if (password.isBlank() || passwordConfirmation.isBlank()) {
            throw new ControlException("A senha e a confirmação não podem estar vazias!");
        }
        if (password.length() < 6) {
            throw new ControlException("A senha tem que ter no mínimo 6 caracteres!");
        }
        if (!password.equals(passwordConfirmation)) {
            throw new ControlException("As senhas não coincidem!");
        }


    }

        public void loginUser(String cpf, String password) throws ControlException{
            if (cpf == null || cpf.isBlank()) {
                throw new ControlException("O CPF é obrigatório!");
            }
            if (password == null || password.isBlank()) {
                throw new ControlException("A senha é obrigatória!");
            }
        }
        }






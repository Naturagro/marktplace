package com.naturagro.controllers;

//lança a exceção de cadastro
public class AccessControlController {
    public void registerUser(String userName, String password, String passwordConfirmation) throws ControlException {
        if (userName == null || userName.trim().isEmpty()) {
            throw new ControlException("O nome do usuário é obrigatório!");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new ControlException("A senha é obrigatória!");
        }
        if (!password.equals(passwordConfirmation)) {
            throw new ControlException("As senhas não coincidem!");
        }
    }
}

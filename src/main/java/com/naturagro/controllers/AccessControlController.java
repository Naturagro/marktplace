package com.naturagro.controllers;

//lança a exceção de cadastro
public class AccessControlController {
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
    }
}

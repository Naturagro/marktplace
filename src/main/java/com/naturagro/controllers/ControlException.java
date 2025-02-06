package com.naturagro.controllers;

//tratamento de exceção
public class ControlException extends Exception{

    public ControlException(String mensagem) {
        super(mensagem);  // chama o construtor da superclasse passando a mensagem como argumento
    }
}

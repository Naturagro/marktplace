package com.naturagro.utils;

import com.naturagro.utils.ValidadorCPF;

public class ValidadorDeCampo {
    public boolean validar(String campo, String valor) {
        if (campo == null || campo.isEmpty() || valor == null || valor.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean validarCampoVazio(String valor) {
        return valor != null && !valor.isEmpty();
    }

    public boolean validarNome(String valor) {
        return valor.matches("[a-zA-Z\\s]+");
    }

    public boolean validarPreco(String valor) {
        try {
            double preco = Double.parseDouble(valor);
            return preco >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean validarQuantidade(String valor) {
        try {
            int quantidade = Integer.parseInt(valor);
            return quantidade >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean validarCPF(String valor) {
        ValidadorCPF validador = new ValidadorCPF(); // Cria um objeto ValidadorCPF
        return validador.validarCPF(valor); // Chama o método validarCPF da classe ValidadorCPF
    }

    public boolean validarSenha(String valor) {
        return valor.length() >= 6;
    }

    public boolean validarConfirmacaoSenha(String senha, String confirmacaoSenha) {
        return senha.equals(confirmacaoSenha);
    }

    public boolean validarEmail(String valor) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return valor.matches(regex);
    }
}
package com.naturagro.utils;

public class ValidadorDeCampo {
    public boolean validar(String campo, String valor) {
        if (campo == null || campo.isEmpty() || valor == null || valor.isEmpty()) {
            return false;
        }
        return true;
    }
}

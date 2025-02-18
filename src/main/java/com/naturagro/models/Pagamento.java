package com.naturagro.models;


public class Pagamento {
    private String id;
    private String tipo;
    private double valor;

    public Pagamento(String id, String tipo, double valor) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getId() { return id; }
    public String getTipo() { return tipo; }
    public double getValor() { return valor; }
}

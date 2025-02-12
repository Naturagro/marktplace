package com.naturagro.example;

import com.naturagro.models.Produto;
import com.naturagro.models.Venda;
import com.naturagro.service.VendaService;

public class ConsultarVenda {
    public static void main(String[] args) {
        VendaService vendaService = new VendaService();
        Venda v = vendaService.obterPorID(36l);

        for (Produto produto : v.getProdutos()) {
            System.out.println(produto.getNome());
        }
        System.out.println();
        System.out.println(v.getOperador().getNome());
    }
}

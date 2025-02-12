package com.naturagro.example;

import com.naturagro.models.Produto;
import com.naturagro.models.Venda;
import com.naturagro.service.VendaService;

public class ConsultarVenda {
    public static void main(String[] args) {
        VendaService vendaService = new VendaService();
        //A parte mais simples na gerência da entidade Venda

        Venda v = vendaService.obterPorID(41l);
        //Para consultar basta passar o id como parâmetros
        //Teremos como retorno o objeto com todos os seus atributos para serem consultados

        for (Produto produto : v.getProdutos()) {
            System.out.println(produto.getNome() + " " + produto.getPrecoVarejo());
        }
        System.out.println();
        System.out.println(v.getOperador().getNome());

        System.out.println(v.obterValorTotal());
    }
}

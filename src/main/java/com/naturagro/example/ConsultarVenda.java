package com.naturagro.example;

import com.naturagro.models.Produto;
import com.naturagro.models.Venda;
import com.naturagro.service.VendaService;

public class ConsultarVenda {
    public static void main(String[] args) {
        VendaService vendaService = new VendaService();
        //A parte mais simples na gerência da entidade Venda

        Venda v = vendaService.obterPorID(37l);
        //Para consultar basta passar o id como parâmetros
        //Teremos como retorno o objeto com todos os seus atributos para serem consultados

        for (Produto produto : v.getProduto()) {
            System.out.println(produto.getNome() + " " + produto.getPreco());
        }
        //todo caso retorne um null pointer é porquê o indice não existe no bd
        System.out.println();
        System.out.println(v.getOperador().getNome());

        System.out.println(v.obterValorTotal());

        System.out.println();
    }
}

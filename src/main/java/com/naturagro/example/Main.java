package com.naturagro.example;

import java.time.LocalDate;

import com.naturagro.models.Operador;
import com.naturagro.models.Produto;
import com.naturagro.service.OperadorService;
import com.naturagro.service.ProdutoService;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        ProdutoService produtoService = new ProdutoService();
        LocalDate dataEntrada = LocalDate.now();
        LocalDate dataVencimento = LocalDate.now().plusDays(30);



        System.out.println();
        System.out.println(dataEntrada);
        System.out.println(dataVencimento);
        Produto produto = new Produto("Produto test", "Produto test", "Produto test",dataEntrada, dataVencimento,
                100, 105.5, 110.1);
        //produtoService.incluirAtomico(produto);

        Operador operador = new Operador("Operador Test", "CPF test");
        OperadorService oS = new OperadorService();
        oS.incluirAtomico(operador);

    }
}

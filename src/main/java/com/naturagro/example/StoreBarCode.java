package com.naturagro.example;

import com.naturagro.models.Produto;
import com.naturagro.service.ProdutoService;
import com.naturagro.utils.BarCodeGenerator;

import java.util.List;

public class StoreBarCode {
    public static void main(String[] args) {

        BarCodeGenerator generator = new BarCodeGenerator();
        ProdutoService produtoService = new ProdutoService();
        List<Produto> produtos = produtoService.obterTodos();

        for (Produto produto : produtos) {
            generator.gerarCodigoProduto(produto);
        }
    }
}

package com.naturagro.controllers;

public class CadastroProdutoController {
    public void registerProduto (String categoria, String codigo, String nomeProduto, String preco, String validade, String fornecedor) throws ControlException{
        if (codigo == null || codigo.isBlank()){
            throw new ControlException("Preencha um código para o produto!");
        }
        //todo: mais validações para cadastro do produto

    }
}

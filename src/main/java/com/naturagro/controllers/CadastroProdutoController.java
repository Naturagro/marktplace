package com.naturagro.controllers;
import com.naturagro.models.Produto;
import com.naturagro.service.ProdutoService;

public class CadastroProdutoController {
    private Produto produto;

    public void registerProduto (Produto produto) throws ControlException {

        if (produto.getNome() == null || produto.getNome().isBlank()) {
            throw new ControlException("Preencha um nome para o produto!");
        }
        if (produto.getCategoria() == null) {
            throw new ControlException("Selecione uma Categoria");

        } else { // Se tudo der certo, amém, senhor, gloria a deus, o produto é adicionado
            ProdutoService produtoService = new ProdutoService();
            produtoService.incluirAtomico(produto);
        }
    }
}

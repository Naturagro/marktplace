package com.naturagro.service;

import com.naturagro.data.DAO;
import com.naturagro.models.Produto;

public class ProdutoService extends DAO<Produto> {

    public ProdutoService() {
        super(Produto.class);
    }
}

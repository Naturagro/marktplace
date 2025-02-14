package com.naturagro.ui;

import com.naturagro.models.Produto;

public interface ProdutoSelecionadoListener {
    void onProdutoSelecionado(Produto produto, Integer quantidade);
}


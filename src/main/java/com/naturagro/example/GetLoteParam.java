package com.naturagro.example;

import com.naturagro.models.Lote;
import com.naturagro.models.Produto;
import com.naturagro.service.LoteService;
import com.naturagro.service.ProdutoService;

import java.time.LocalDate;
import java.util.List;

public class GetLoteParam {
    public static void main(String[] args) {

        ProdutoService produtoService = new ProdutoService();
        LoteService loteService = new LoteService();
        List<Produto> produtos = produtoService.buscarPorNome("Maçã");
        Lote lote1 = new Lote(produtos.get(0), LocalDate.now(), 23);
        loteService.incluirAtomico(lote1);
        Lote lote = loteService.consultarLotePorProduto(produtos.get(0), 5);

        System.out.println(lote.getProduto().getNome());
    }
}

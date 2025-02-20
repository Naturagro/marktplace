package com.naturagro.example;


import com.naturagro.models.Lote;
import com.naturagro.models.Produto;
import com.naturagro.service.LoteService;
import com.naturagro.service.ProdutoService;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class AdicionarLote {
        public void  adicionarL() {
            ProdutoService produtoService = new ProdutoService();
            List<Produto> produtos = produtoService.obterTodos();

            Random random = new Random();

            LoteService loteService = new LoteService();
            for (Produto produto : produtos) {
                Lote lote1 = new Lote(produtoService.buscarPorNome(produto.getNome()).get(0), LocalDate.now(),
                        random.nextInt(0, 1000),
                        LocalDate.now().plusMonths(6));
                Lote lote2 = new Lote(produtoService.buscarPorNome(produto.getNome()).get(0),
                        LocalDate.now().minusDays(10), random.nextInt(0, 100), LocalDate.now().plusMonths(4));
                loteService.incluirAtomico(lote1);
                loteService.incluirAtomico(lote2);
            }
        }
}

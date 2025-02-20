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
            int contador = 0;

            LoteService loteService = new LoteService();
            for (Produto produto : produtos) {
                Lote lote1 = new Lote(
                        produtoService.obterPorID(produto.getId()),
                        LocalDate.now(),
                        random.nextInt(0, 1000),
                        LocalDate.now().plusMonths(6));
                System.out.println("Adicionado ao lote1 " + lote1.getProduto().getNome());
                Lote lote2 = new Lote(
                        produtoService.obterPorID(produto.getId()),
                        LocalDate.now().minusDays(10),
                        random.nextInt(0, 100),
                        LocalDate.now().plusMonths(
                                random.nextInt(0, 12)));
                System.out.println("Adicionado ao lote2 " + lote2.getProduto().getNome());
                System.out.println("Contador: " + contador);
                contador += 1;
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                loteService.incluirAtomico(lote1);
                loteService.incluirAtomico(lote2);
            }
        }
}

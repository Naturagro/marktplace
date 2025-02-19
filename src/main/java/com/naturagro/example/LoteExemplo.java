package com.naturagro.example;

import com.naturagro.models.Lote;
import com.naturagro.models.Produto;
import com.naturagro.service.LoteService;
import com.naturagro.service.ProdutoService;

import java.time.LocalDate;
import java.util.List;

public class LoteExemplo {
    public static void main(String[] args) {

        /*
        Lote e Produto são duas entidades correlatas
        cada loté conterá uma referência a um tipo de produto
        Podem existir vários lotes de produtos

        A entidade lote conhece a produto, por chave extrangeira
        Mas é desconhecida por Produto
         */
        ProdutoService produtoService = new ProdutoService();
        List<Produto> produtos = produtoService.obterTodos();

        Lote lote = new Lote(produtos.get(2), LocalDate.now(), 0,LocalDate.now()); // defini a data de vencimento como agr só pq eu n sei oq fazer e isso é só um exemplo

        LoteService loteService = new LoteService();
        loteService.incluirAtomico(lote);
    }
}

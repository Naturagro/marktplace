package com.naturagro.example;

import com.naturagro.models.Lote;
import com.naturagro.service.LoteService;

import java.util.List;

public class ConsultarLote {
    public static void main(String[] args) {

        LoteService loteService = new LoteService();
        List<Lote> lotes = loteService.obterTodos();

        for (Lote lote : lotes) {
            System.out.println(lote.getId());
            System.out.println(lote.getProduto().getNome());
        }
    }
}

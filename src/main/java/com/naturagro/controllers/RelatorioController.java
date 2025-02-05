package com.naturagro.controllers;

import com.naturagro.models.Venda;
import java.util.List;

public class RelatorioController {

    public String gerarRelatorioVendas(List<Venda> vendas) {
        if (vendas == null || vendas.isEmpty()) {
            return "Nenhuma venda registrada!";
        }

        StringBuilder relatorio = new StringBuilder("==== Relatório de Vendas ====\n");
        for (Venda venda : vendas) {
            relatorio.append("Venda ID: ").append(venda.getId()).append("\n");
            relatorio.append("Data: ").append(venda.getDataCompra()).append("\n");
            relatorio.append("Valor Total: R$ ").append(venda.getValorTotal()).append("\n");
            relatorio.append("----------------------------\n");
        }

        return relatorio.toString();
    }
}

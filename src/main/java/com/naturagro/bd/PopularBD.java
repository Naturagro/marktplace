package com.naturagro.bd;

import com.naturagro.models.Funcionario;
import com.naturagro.models.Lote;
import com.naturagro.models.Produto;
import com.naturagro.models.Venda;
import com.naturagro.service.FuncionarioService;
import com.naturagro.service.LoteService;
import com.naturagro.service.ProdutoService;
import com.naturagro.service.VendaService;

import java.util.List;

public class PopularBD {

    public void popular() {
        adicionarProdutos();
        adicionarFuncionarios();
        adicionarLotes();
        adicionarVendas();
    }

    public void adicionarProdutos() {
        ProdutoService produtoService = new ProdutoService();
        List<Produto> produtos = produtoService.obterTodos(0, 2);
        if (produtos == null  || produtos.size() == 0) {
            AdicionarProdutos adicionarProdutos = new AdicionarProdutos();
            adicionarProdutos.adicionarP();
        }
    }

    public void adicionarFuncionarios() {
        FuncionarioService funcionarioService = new FuncionarioService();
        List<Funcionario> funcionarios = funcionarioService.obterTodos(0, 2);
        if (funcionarios == null  || funcionarios.size() == 0) {
            AdicionarFuncionarios adicionarFuncionarios = new AdicionarFuncionarios();
            adicionarFuncionarios.adicionarF();
        }
    }

    public void adicionarLotes() {
        LoteService loteService = new LoteService();
        List<Lote> lotes = loteService.obterTodos(0, 2);
        if (lotes == null  || lotes.size() == 0) {
            AdicionarLote adicionarLote = new AdicionarLote();
            adicionarLote.adicionarL();
        }
    }

    public void adicionarVendas() {
        VendaService vendaService = new VendaService();
        List<Venda> vendas = vendaService.obterTodos(0, 2);
        if (vendas == null  || vendas.size() == 0) {
            AdicionarVenda adicionarVenda = new AdicionarVenda();
            adicionarVenda.adicionarV();
        }
    }
}

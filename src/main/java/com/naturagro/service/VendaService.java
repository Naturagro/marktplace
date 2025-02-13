package com.naturagro.service;

import com.naturagro.DAO.DAO;
import com.naturagro.models.Produto;
import com.naturagro.models.Venda;


import java.time.LocalDateTime;
import java.util.List;


public class VendaService extends DAO<Venda> {

    ProdutoService produtoService = new ProdutoService();
    FuncionarioService funcionarioService = new FuncionarioService();

    public VendaService() {
        super(Venda.class);
    }


    public void salvarVenda(Venda venda) {
        this.abrirT();

        for (Produto produto : venda.getProduto()) {
            if (produto.getId() == null) {
                produtoService.incluirAtomico(produto);
            } else {
                produto = produtoService.obterPorID(produto.getId());
            }
        }

        if (venda.getOperador().getId() == null) {
            funcionarioService.incluirAtomico(venda.getOperador());
        } else {
            venda.setOperador(funcionarioService.obterPorID(venda.getOperador().getId()));
        }

        mesclar(venda);
        fecharT();
        fechar();


    }
    // Busca vendas dentro de um intervalo de datas
    public List<Venda> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return consultar("Venda.buscarPorPeriodo", "inicio", inicio, "fim", fim);
    }

    // Busca vendas realizadas por um operador específico
    public List<Venda> buscarPorOperador(Long operadorId) {
        return consultar("Venda.buscarPorOperador", "operadorId", operadorId);
    }

    // Busca vendas que contenham um determinado produto
    public List<Venda> buscarPorProduto(Long produtoId) {
        return consultar("Venda.buscarPorProduto", "produtoId", produtoId);
    }

    // Busca vendas que contenham produtos de uma determinada categoria
    public List<Venda> buscarPorCategoria(String categoria) {
        return consultar("Venda.buscarPorCategoria", "categoria", categoria);
    }

    // Conta o total de vendas registradas no sistema
    public Long contarTotalVendas() {
        return consultarUm("Venda.contarTotalVendas").getId();
    }

    // Retorna o valor total das vendas dentro de um determinado período
    public Venda somarValorTotalPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return consultarUm("Venda.somarValorTotalPorPeriodo", "inicio", inicio, "fim", fim);
    }
}

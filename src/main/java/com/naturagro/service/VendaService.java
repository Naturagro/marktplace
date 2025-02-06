package com.naturagro.service;

import com.naturagro.DAO.DAO;
import com.naturagro.models.Produto;
import com.naturagro.models.Venda;


public class VendaService extends DAO<Venda> {

    ProdutoService produtoService = new ProdutoService();
    FuncionarioService funcionarioService = new FuncionarioService();
    public VendaService() {
        super(Venda.class);
    }


    public void salvarVenda(Venda venda) {
        this.abrirT();
        for (Produto produto : venda.getProdutos()) {
            if (produto.getId() == null) {
                produtoService.incluirAtomico(produto);  // Persistir produtos novos
            } else {
                produtoService.obterPorID(produto.getId());  // Reatachar produtos desanexados
            }
        }
        if(venda.getOperador().getCpf() == null) {
            funcionarioService.incluirAtomico(venda.getOperador());
        } else {
            funcionarioService.obterPorID(venda.getOperador().getCpf());
        }
        mesclar(venda);
        fecharT();
        fechar();
    }
}
 /*
   for (Produto produto : venda.getProdutos()) {
            if (produto.getId() == null) {
                produtoService.incluirAtomico(produto);  // Persistir produtos novos
            } else {
                produtoService.obterPorID(produto.getId());  // Reatachar produtos desanexados
            }
        }


        if(venda.getOperador().getId() == null) {
            funcionarioService.incluirAtomico();
        } else {
            funcionarioService.obterPorID();
        }
  */

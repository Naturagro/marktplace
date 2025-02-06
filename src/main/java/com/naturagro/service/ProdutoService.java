package com.naturagro.service;

import com.naturagro.DAO.DAO;
import com.naturagro.models.Produto;
import java.time.LocalDate;
import java.util.List;

public class ProdutoService extends DAO<Produto> {

    public ProdutoService() {
        super(Produto.class);
    }

    // Buscar Produtos Próximos do Vencimento (menos de 5 dias para vencer)
    public List<Produto> buscarProdutosPertoVencimento() {
        return consultar("Produto.buscarPertoVencimento", "hoje", LocalDate.now(), "limite", LocalDate.now().plusDays(5));
    }

    // Buscar Produtos com Estoque Baixo (menos de 10 unidades)
    public List<Produto> buscarProdutosComEstoqueBaixo() {
        return consultar("Produto.buscarEstoqueBaixo", "quantidade", 10);
    }

    // Atualizar Estoque após Venda
    public void atualizarEstoque(Long id, int quantidadeVendida) {
        Produto produto = obterPorID(id);
        if (produto != null) {
            int novoEstoque = produto.getQuantidadeEmEstoque() - quantidadeVendida;
            if (novoEstoque < 0) {
                throw new IllegalArgumentException("Estoque insuficiente para essa venda!");
            }
            produto.setQuantidadeEmEstoque(novoEstoque);
            mesclar(produto); // Atualiza a entidade no banco
        } else {
            throw new RuntimeException("Produto não encontrado!");
        }
    }
}

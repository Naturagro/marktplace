package com.naturagro.service;

import com.naturagro.DAO.DAO;
import com.naturagro.models.Produto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class ProdutoService extends DAO<Produto> {

    public ProdutoService() {
        super(Produto.class);
    }

    // Buscar Produtos Próximos do Vencimento (menos de 5 dias para vencer)
    public List<Produto> buscarProdutosPertoVencimento() {
        String jpql = "SELECT p FROM Produto p WHERE p.dataVencimento BETWEEN :hoje AND :limite";
        return consultar(jpql, "hoje", LocalDate.now(), "limite", LocalDate.now().plusDays(5));
    }

    // Buscar Produtos com Estoque Baixo (menos de 10 unidades)
    public List<Produto> buscarProdutosComEstoqueBaixo() {
        String jpql = "SELECT p FROM Produto p WHERE p.quantidadeEmEstoque < :quantidade";
        return consultar(jpql, "quantidade", 10);
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

    public List<Object[]> buscarPerfilProduto() {
        ProdutoService produtoService = new ProdutoService();
        EntityManager em = produtoService.getEntityManager();
        String jpql = "SELECT p.id, p.categoria, p.descricao, p.nome, p.precoAtacado, p.precoVarejo FROM Produto p";
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        return query.getResultList();
    }
}

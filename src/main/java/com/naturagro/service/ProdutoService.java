package com.naturagro.service;

import com.naturagro.DAO.DAO;
import com.naturagro.models.CategoriaProduto;
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
        EntityManager em = getEntityManager();
        TypedQuery<Produto> query = em.createNamedQuery("Produto.buscarPertoVencimento", Produto.class);
        query.setParameter("hoje", LocalDate.now());
        query.setParameter("limite", LocalDate.now().plusDays(5));
        return query.getResultList();
    }

    //  Buscar Produtos com Estoque Baixo (menos de 10 unidades)
    public List<Produto> buscarProdutosComEstoqueBaixo() {
        EntityManager em = getEntityManager();
        TypedQuery<Produto> query = em.createNamedQuery("Produto.buscarEstoqueBaixo", Produto.class);
        query.setParameter("quantidade", 10);
        return query.getResultList();
    }

    // Buscar produtos por nome
    public List<Produto> buscarPorNome(String nome) {
        EntityManager em = getEntityManager();
        TypedQuery<Produto> query = em.createNamedQuery("Produto.buscarPorNome", Produto.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }

    //  Buscar produtos por categoria
    public List<Produto> buscarPorCategoria(CategoriaProduto categoria) {
        EntityManager em = getEntityManager();
        TypedQuery<Produto> query = em.createNamedQuery("Produto.buscarPorCategoria", Produto.class);
        query.setParameter("categoria", categoria);
        return query.getResultList();
    }

    //  Buscar produtos p/preço
    public List<Produto> buscarPorPreco(double precoMin, double precoMax) {
        EntityManager em = getEntityManager();
        TypedQuery<Produto> query = em.createNamedQuery("Produto.buscarPorFaixaDePreco", Produto.class);
        query.setParameter("precoMin", precoMin);
        query.setParameter("precoMax", precoMax);
        return query.getResultList();
    }

    //  Atualizar Estoque após Venda
    public void atualizarEstoque(Long id, int quantidadeVendida) {
        EntityManager em = getEntityManager();
        Produto produto = obterPorID(id);

        if (produto != null) {
            int novoEstoque = produto.getQuantidadeEmEstoque() - quantidadeVendida;
            if (novoEstoque < 0) {
                throw new IllegalArgumentException("Estoque insuficiente para essa venda!");
            }
            produto.setQuantidadeEmEstoque(novoEstoque);

            em.getTransaction().begin();
            mesclar(produto);
            em.getTransaction().commit();
        } else {
            throw new RuntimeException("Produto não encontrado!");
        }
    }

    // todo mudar esse método por algum outro
    public List<Object[]> buscarPerfilProduto() {
        ProdutoService produtoService = new ProdutoService();
        EntityManager em = produtoService.getEntityManager();
        String jpql = "SELECT p.id, p.categoria, p.descricao, p.nome, p.preco, p.quantidadeEmEstoque FROM Produto p";
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        return query.getResultList();
    }
}

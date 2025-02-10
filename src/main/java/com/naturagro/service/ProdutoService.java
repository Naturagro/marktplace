package com.naturagro.service;

import com.naturagro.DAO.DAO;
import com.naturagro.models.Produto;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;

public class ProdutoService extends DAO<Produto> {
    private final EntityManager em;

    public ProdutoService() {
        super(Produto.class);
        // Coloquei o Entity manager aqui pq tava precisando instanciar um ProdutoService e pegar o EntityManager toda maldita vez que eu usava em um méto_do
        this.em = getEntityManager();
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

    // Realiza uma busca do perfil de produto (sem data de entrada/vencimento e quantidade me estoque)
    public List<Object[]> buscarPerfilProduto() {
        String jpql = "SELECT p.id, p.categoria, p.descricao, p.nome, p.precoAtacado, p.precoVarejo FROM Produto p";
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        return query.getResultList();
    }

    public void excluirProduto(long id) {
        try {
            // Inicia a transação
            em.getTransaction().begin();

            // Encontra o produto pelo parametro ID recebido
            Produto produto = em.find(Produto.class, id);
            if (produto != null) {
                em.remove(produto);
            }

            // Envia as alterações
            em.getTransaction().commit();

        } catch (Exception e) {

            // Em caso de dar merda desfaz a transação
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}

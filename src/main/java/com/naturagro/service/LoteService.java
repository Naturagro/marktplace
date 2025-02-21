package com.naturagro.service;

import com.naturagro.DAO.DAO;
import com.naturagro.models.Lote;
import com.naturagro.models.Produto;
import com.naturagro.models.Venda;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class LoteService extends DAO<Lote> {
    public LoteService() {
        super(Lote.class);
    }

    public Lote consultarLotePorProduto(Produto produto, int quantidade) {
        String consulta = "SELECT l FROM Lote l WHERE l.produto.id = :produtoId AND l.quantidade >= :quantidade ORDER BY l.dataVencimento ASC";
        EntityManager em = getEntityManager();
        TypedQuery<Lote> query = em.createQuery(consulta, Lote.class);
        query.setParameter("quantidade", quantidade);
        query.setParameter("produtoId", produto.getId());

        List<Lote> lotes = query.getResultList();

        if (lotes.isEmpty()) {
            return null; // Nenhum lote encontrado
        }

        return lotes.get(0); // Retorna o primeiro lote disponível (por exemplo, o de validade mais próxima)
    }

    public LoteService rollBackT() {
        EntityManager em = getEntityManager();
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        return this;
    }

    public List<Lote> consultarLotePorData(LocalDate data){

        String consulta = "SELECT l FROM Lote l WHERE l.dataEntrada = :data";
        EntityManager em = getEntityManager();
        TypedQuery<Lote> query = em.createQuery(consulta, Lote.class);
        query.setParameter("data", data);

        List<Lote> lotes = query.getResultList();
        return lotes;
    }

//
//        <named-query name="Lote.consultarPorProdutoQntd">
//            <query>SELECT l FROM Lote l WHERE l.quantidade > :quantidade and l.produto_id = :produto_id ORDER BY
//    l.quantidade ASC</query>
//    </named-query>


}

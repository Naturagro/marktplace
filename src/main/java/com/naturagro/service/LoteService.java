package com.naturagro.service;

import com.naturagro.DAO.DAO;
import com.naturagro.models.Lote;
import com.naturagro.models.Produto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class LoteService extends DAO<Lote> {
    public LoteService() {
        super(Lote.class);
    }

    public Lote consultarLotePorProduto(Produto produto, int quantidade) {
//        Lote lote = consultarUm("Lote.consultarPorProdutoQntd", quantidade, produto.getId());
//        return lote;
        String consulta = "SELECT l FROM Lote l WHERE l.produto.id = :produtoId AND l.quantidade >= :quantidade";
        EntityManager em = getEntityManager();
        TypedQuery<Lote> query = em.createQuery(consulta, Lote.class);
        query.setParameter("quantidade", quantidade);
        query.setParameter("produtoId", produto.getId());

        return query.getSingleResult();

    }
//
//        <named-query name="Lote.consultarPorProdutoQntd">
//            <query>SELECT l FROM Lote l WHERE l.quantidade > :quantidade and l.produto_id = :produto_id ORDER BY
//    l.quantidade ASC</query>
//    </named-query>


}

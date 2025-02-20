package com.naturagro.DAO;

import com.naturagro.bd.CreateSchema;

import javax.persistence.*;
import java.util.List;

public abstract class DAO <E>{
    public static EntityManagerFactory emf;
    private EntityManager em;
    private Class<E> classe;

    //todo revisar
    static {
        try {
            emf = Persistence
                    .createEntityManagerFactory("naturagro");
        } catch (Exception e) {
            CreateSchema createSchema = new CreateSchema();
            createSchema.createSchema();
            //e.printStackTrace();
        }
    }

    public DAO() {
        this(null);
    }

    public DAO(Class<E> classe) {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("naturagro");
        }
        this.classe = classe;
        em = emf.createEntityManager();
    }

    // Execute antes de fazer qualquer operação no Banco que não seja uma consulta
    public DAO<E> abrirT() {
        em.getTransaction().begin();
        return this;
    }
    // Execute no fim do código, depois do codigo que mexe no Banco
    public EntityTransaction obterTransaction() {
        return em.getTransaction();
    }
    public DAO<E> fecharT() {
        em.getTransaction().commit();
        return this;
    }
    //getTransaction().begin().getTransaction().commit().getTransaction().close()

    // Execute passando um objeto entidade como parametro para adicionar o objeto na sua tabela
    public DAO<E> incluir(E entidade) {
        em.persist(entidade);
        return this;
    }

    public Object mesclar(E entidade) {
        return em.merge(entidade);
    }

    public DAO<E> incluirAtomico(E entidade) {
        return this.abrirT().incluir(entidade).fecharT();
    }

    public E obterPorID(Object id) {
        return em.find(classe, id);
    }

    public List<E> obterTodos() {
        return this.obterTodos(10, 0);
    }

    public List<E> obterTodos(int qtde, int deslocamento) {
        if(classe == null) {
            throw new UnsupportedOperationException("Classe nula.");
        }

        String jpql = "select e from " + classe.getName() + " e";
        TypedQuery<E> query = em.createQuery(jpql, classe);
        query.setMaxResults(qtde);
        query.setFirstResult(deslocamento);
        return query.getResultList();
    }

    public List<E> consultar(String nomeConsulta, Object... params) {
        TypedQuery<E> query = em.createNamedQuery(nomeConsulta, classe);

        for (int i = 0; i < params.length; i += 2) {
            query.setParameter(params[i].toString(), params[i + 1]);
        }

        return query.getResultList();
    }

    public E consultarUm(String nomeConsulta, Object... params) {
        List<E> lista = consultar(nomeConsulta, params);
        return lista.isEmpty() ? null : lista.get(0);
    }
    public void remover(E entidade) {
        em.getTransaction().begin();
        if (!em.contains(entidade)) {
            entidade = em.merge(entidade);
        }
        em.remove(entidade);
        em.getTransaction().commit();
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void fechar() {
        em.close();
    }
}

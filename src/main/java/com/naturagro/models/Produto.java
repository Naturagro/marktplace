package com.naturagro.models;


import com.naturagro.service.ProdutoService;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.naturagro.utils.KeyGenerator.generateKey;


@Entity
public class Produto {

    @Id
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    @Enumerated(EnumType.STRING)
    private CategoriaProduto categoria;
    @ManyToMany(mappedBy = "produto", fetch = FetchType.EAGER)
    private List<Venda> venda = new ArrayList<>();

    public Produto(
            String nome,
            String descricao,
            Double preco,
            CategoriaProduto categoriaProduto
    ) {
        this.id = validarId();
        this.preco = preco;
        this.categoria = categoriaProduto;
        this.descricao = descricao;
        this.nome = nome;
    }

    public Produto() { // precisei deixar publico pq eu tive q criar um objeto Produto só pra usar em uma logica lá da UI, daí n podia ter id novo
    }

    public Long validarId() {
        Long id = Long.parseLong(generateKey());
        ProdutoService produtoService = new ProdutoService();
        Long idBanco = null;
        try {
            idBanco = produtoService.obterPorID(id).getId();

        } catch (Exception e) {
            if (idBanco == null) {
                this.id = id;
            } else {
                this.id = Long.parseLong(generateKey());
            }
        }
        return id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public CategoriaProduto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProduto categoria) {
        this.categoria = categoria;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public List<Venda> getVenda() {
        return venda;
    }

    public void setVenda(List<Venda> vendas) {
        this.venda = vendas;
    }

}


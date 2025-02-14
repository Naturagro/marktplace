package com.naturagro.models;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String descricao;
    private LocalDate dataEntrada;
    private LocalDate dataVencimento;
    private Integer quantidadeEmEstoque;
    private Double preco;
    @Enumerated(EnumType.STRING)
    private CategoriaProduto categoria;

    @ManyToMany(mappedBy = "produto", fetch = FetchType.EAGER)
    private List<Venda> venda = new ArrayList<>();

    public Produto(
            String nome,
            String descricao,
            Integer quantidadeEmEstoque,
            Double preco,
            CategoriaProduto categoriaProduto) {
        this.preco = preco;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.categoria = categoriaProduto;
        this.descricao = descricao;
        this.nome = nome;
        this.dataEntrada = LocalDate.now();
        this.dataVencimento = calcularVencimento(categoriaProduto);
    }

    protected Produto() {
    }
    private LocalDate calcularVencimento(CategoriaProduto categoriaProduto) {
        LocalDate dataVence = LocalDate.now();
        switch (categoria) {
            case Frutas:
                dataVence = dataVence.plusDays(7);
                break;
            case Carnes:
                dataVence = dataVence.plusDays(30);
                break;
            case Peixes:
                dataVence = dataVence.plusDays(20);
                break;
            case Bebidas:
                dataVence = dataVence.plusDays(365);
                break;
            case Higiene:
                dataVence = dataVence.plusDays(830);
                break;
            case Limpeza:
                dataVence = dataVence.plusDays(830);
                break;
            case Verduras:
                dataVence = dataVence.plusDays(7);
                break;
            case Alimentos:
                dataVence = dataVence.plusDays(180);
                break;
            case Cosmeticos:
                dataVence = dataVence.plusDays(1095);
                break;
            case Laticinios:
                dataVence = dataVence.plusDays(15);
                break;
        }
        return dataVence;
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

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada() {
        this.dataEntrada = LocalDate.now();
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Integer getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(Integer quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
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



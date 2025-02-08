package com.naturagro.models;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@NamedQueries({
        @NamedQuery(
                name = "Produto.buscarPertoVencimento",
                query = "SELECT p FROM Produto p WHERE p.dataVencimento BETWEEN :hoje AND :limite"
        ),
        @NamedQuery(
                name = "Produto.buscarEstoqueBaixo",
                query = "SELECT p FROM Produto p WHERE p.quantidadeEmEstoque < :quantidade"
        )
})
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String descricao;
    private LocalDate dataEntrada;
    private LocalDate dataVencimento;
    private Integer quantidadeEmEstoque;
    private Double precoVarejo;
    private Double precoAtacado;
    @Enumerated(EnumType.STRING)
    private CategoriaProduto categoria;

    @ManyToMany(mappedBy = "produtos")
    private List<Venda> vendas = new ArrayList<>();

    public Produto(
            String nome,
            String descricao,
            CategoriaProduto categoria,
            LocalDate dataEntrada,
            //LocalDate dataVencimento,
            Integer quantidadeEmEstoque,
            Double precoVarejo,
            Double precoAtacado,
            CategoriaProduto categoriaProduto) {
        this.precoAtacado = precoAtacado;
        this.precoVarejo = precoVarejo;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        //this.dataVencimento = calcularVencimento(dataEntrada);
        this.dataVencimento = dataEntrada.plusDays(40);
        this.dataEntrada = dataEntrada;
        this.categoria = categoria;
        this.descricao = descricao;
        this.nome = nome;
        //calcularVencimento(dataEntrada, categoriaProduto)
    }

    public Produto() {}

    private LocalDate calcularVencimento(LocalDate dataEntrada, CategoriaProduto categoriaProduto) {
        //todo adicionar diferentes datas de vencimento para cada categoria
        LocalDate dataVence = dataEntrada.plusDays(20);
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

    public Double getPrecoVarejo() {
        return precoVarejo;
    }

    public void setPrecoVarejo(Double precoVarejo) {
        this.precoVarejo = precoVarejo;
    }

    public Double getPrecoAtacado() {
        return precoAtacado;
    }

    public void setPrecoAtacado(Double precoAtacado) {
        this.precoAtacado = precoAtacado;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }
}



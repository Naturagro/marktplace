package com.naturagro.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

import static com.naturagro.utils.KeyGenerator.generateKey;

@Entity
public class Lote {

    @Id
    private Long id;
    private LocalDate dataEntrada;
    private LocalDate dataVencimento;
    @ManyToOne
    private Produto produto;
    private Integer quantidade;

    public Lote(
            Produto produto,
            LocalDate dataEntrada,
            Integer quantidade
    ) {
        this.id = Long.parseLong(generateKey());
        this.dataEntrada = dataEntrada;
        this.produto = produto;
        this.quantidade = quantidade;
        this.dataVencimento = calcularVencimento(produto);

    }

    protected Lote() {

    }

    private LocalDate calcularVencimento(Produto produto) {
        LocalDate dataVencimento = LocalDate.now();
        switch (produto.getCategoria()) {
            case Frutas:
                dataVencimento = dataVencimento.plusDays(7);
                break;
            case Carnes:
                dataVencimento = dataVencimento.plusDays(30);
                break;
            case Peixes:
                dataVencimento = dataVencimento.plusDays(20);
                break;
            case Bebidas:
                dataVencimento = dataVencimento.plusDays(365);
                break;
            case Higiene:
                dataVencimento = dataVencimento.plusDays(830);
                break;
            case Limpeza:
                dataVencimento = dataVencimento.plusDays(830);
                break;
            case Verduras:
                dataVencimento = dataVencimento.plusDays(7);
                break;
            case Alimentos:
                dataVencimento = dataVencimento.plusDays(180);
                break;
            case Cosmeticos:
                dataVencimento = dataVencimento.plusDays(1095);
                break;
            case Laticinios:
                dataVencimento = dataVencimento.plusDays(15);
                break;
        }
        return dataVencimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}

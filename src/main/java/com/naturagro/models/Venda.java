package com.naturagro.models;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Venda {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime dataCompra;
    private Double valorTotal;

    @ManyToOne
    @JoinColumn(name="operador_id")
    private Funcionario operador;

    @ManyToMany(cascade = CascadeType.ALL) //todo revisar a necessidade do CascadeType.ALL
    private List<Produto> produtos = new ArrayList<>();

    public Venda(LocalDateTime dataCompra, Double valorTotal, Funcionario operador, List<Produto> produtos) {
        this.dataCompra = dataCompra;
        this.valorTotal = valorTotal;
        this.operador = operador;
        this.produtos = produtos;
    }

    public Venda() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Funcionario getOperador() {
        return operador;
    }

    public void setOperador(Funcionario operador) {
        this.operador = operador;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    /*
    @JoinTable(name = "vendas_itens",
    joinColumns = @JoinColumn(name = "venda_id"),
    inverseJoinColumns = @JoinColumn(name = "produto_id"))

    produtoService.obterPorID(produto.getId());
        oS.obterPorID(produto.getId());
     */
}

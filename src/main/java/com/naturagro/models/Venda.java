package com.naturagro.models;


import com.naturagro.service.ProdutoService;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Venda {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime dataCompra;
    private Double valorTotal;

    @ManyToOne
    @JoinColumn(name="operador_id")
    private Funcionario operador;

    @ManyToMany(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER
    )
    private List<Produto> produto = new ArrayList<>();

    public Venda(Funcionario operador, List<Produto> produtos) {
        this.operador = operador;
        this.produto = produtos;
        this.dataCompra = LocalDateTime.now();
        this.valorTotal = obterValorTotal();

        //atualizarEstoqueVenda(produtos);
    }

    public int freqProdutoVenda(Long id, List<Produto> produtos) {
        int qtd = 0;
        for (Produto produto : produtos) {
            if (Objects.equals(produto.getId(), this.id)) qtd++;
        }
        return qtd;
    }

//    public void atualizarEstoqueVenda(List<Produto> produtos) {
//        ProdutoService pS = new ProdutoService();
//        int qtd = 0;
//        for (Produto produto : produtos) {
//            qtd = freqProdutoVenda(produto.getId(), produtos);
//            pS.atualizarEstoque(produto.getId(), qtd);
//        }
//    }

    public Double obterValorTotal() {
        Double soma = this.produto.stream().map(p -> p.getPreco() ).reduce(0.0, Double::sum);
        return soma;
    }

    public Venda() {
        this.dataCompra = LocalDateTime.now();
        this.valorTotal = obterValorTotal();
    }

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

    public Funcionario getOperador() {
        return operador;
    }

    public void setOperador(Funcionario operador) {
        this.operador = operador;
    }

    public List<Produto> getProduto() {
        return produto;
    }

    public void setProduto(List<Produto> produtos) {
        this.produto = produtos;
        this.valorTotal = obterValorTotal();
    }
}

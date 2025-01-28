package com.naturagro.models;

import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Transactional
public class Venda {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime dataCompra;
    private Double valorTotal;

    @ManyToOne
    @JoinColumn(name="operador_id")
    private Operador operador;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Produto> produtos = new ArrayList<>();

    public Venda(LocalDateTime dataCompra, Double valorTotal, Operador operador, List<Produto> produtos) {
        this.dataCompra = dataCompra;
        this.valorTotal = valorTotal;
        this.operador = operador;
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

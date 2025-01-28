package naturagro.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String descricao;
    private String categoria;
    private LocalDate dataEntrada;
    private LocalDate dataVencimento;
    private Integer quantidadeEmEstoque;
    private Double precoVarejo;
    private Double precoAtacado;

    @ManyToMany(mappedBy = "produtos")
    private List<Venda> vendas = new ArrayList<>();

    public Produto(
            String nome,
            String descricao,
            String categoria,
            LocalDate dataEntrada,
            LocalDate dataVencimento,
            Integer quantidadeEmEstoque,
            Double precoVarejo,
            Double precoAtacado) {
        this.precoAtacado = precoAtacado;
        this.precoVarejo = precoVarejo;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        //this.dataVencimento = calcularVencimento(dataEntrada);
        this.dataVencimento = dataVencimento;
        this.dataEntrada = dataEntrada;
        this.categoria = categoria;
        this.descricao = descricao;
        this.nome = nome;
    }

    private LocalDate calcularVencimento(LocalDate dataEntrada) {
        LocalDate dataVence = dataEntrada.plusDays(20);
        return dataVence;
    }
}



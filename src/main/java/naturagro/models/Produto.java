package naturagro.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    private Long id;
    private String nome;
    private String descricao;
    private String categoria;
    private LocalDate dataEntrada;
    private LocalDate dataVencimento;
    private Integer quantidadeEmEstoque;
    private Double precoVarejo;
    private Double precoAtacado;

    private void calculaVencimento() {
        //todo
        //we can use .plusDays(numberOfDays);
        String[] dataEntradaStr = dataEntrada.toString().split("-");
        Integer dataVencimento = Integer.parseInt(dataEntradaStr[2]) + 2;
        LocalDate dataVence = LocalDate.of(2025, 01, dataVencimento);
    }
}

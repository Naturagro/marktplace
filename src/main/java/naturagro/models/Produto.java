package naturagro.models;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
public class Produto {

    @Id
    private Long id;
    private String nome;
    private String descricao;
    private String categoria;
    private LocalDate dataVencimento;
    private LocalDate dataEntrada;
    private Integer quantidadeEmEstoque;
    private Double precoVarejo;
    private Double precoAtacado;

}

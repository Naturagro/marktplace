package naturagro.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
public class Venda {

    @Id
    private Long id;
    private LocalDateTime dataCompra;

    @ManyToOne
    @JoinColumn(name="operador_id")
    private Operador operador;

    @OneToMany
    private List<Produto> produtos;
    private Double valorTotal;

}

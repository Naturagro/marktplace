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

    private Operador operador;

    @OneToMany(mappedBy = "venda", fetch = FetchType.EAGER)
    private List<Produto> produtos;
    private Double valorTotal;

}

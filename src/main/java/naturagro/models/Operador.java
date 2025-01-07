package naturagro.models;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Operador {

    private Long id;
    private String nome;
    private String cpf;
    private String senha;


}

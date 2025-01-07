package naturagro.service;

import naturagro.DAO.DAO;
import naturagro.models.Operador;

public class OperadorService extends DAO<Operador> {

    public OperadorService() {
        super(Operador.class);
    }
}

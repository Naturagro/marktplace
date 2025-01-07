package naturagro.service;

import naturagro.DAO.DAO;
import naturagro.models.Venda;

public class CompraService extends DAO<Venda> {

    public CompraService() {
        super(Venda.class);
    }
}

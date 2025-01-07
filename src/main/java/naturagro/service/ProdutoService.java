package naturagro.service;

import naturagro.DAO.DAO;
import naturagro.models.Produto;

public class ProdutoService extends DAO<Produto> {

    public ProdutoService() {
        super(Produto.class);
    }
}

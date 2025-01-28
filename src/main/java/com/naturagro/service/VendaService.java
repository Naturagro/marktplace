package naturagro.service;

import naturagro.DAO.DAO;
import naturagro.models.Operador;
import naturagro.models.Produto;
import naturagro.models.Venda;

import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class VendaService extends DAO<Venda> {

    ProdutoService produtoService = new ProdutoService();
    OperadorService operadorService = new OperadorService();
    public VendaService() {
        super(Venda.class);
    }



    public void salvarVenda(Venda venda) {
        this.abrirT();
        for (Produto produto : venda.getProdutos()) {
            if (produto.getId() == null) {
                produtoService.incluirAtomico(produto);  // Persistir produtos novos
            } else {
                produtoService.obterPorID(produto.getId());  // Reatachar produtos desanexados
            }
        }
        if(venda.getOperador().getId() == null) {
            operadorService.incluirAtomico(venda.getOperador());
        } else {
            operadorService.obterPorID(venda.getOperador().getId());
        }
        mesclar(venda);
        fecharT();
        fechar();
    }
}
 /*
   for (Produto produto : venda.getProdutos()) {
            if (produto.getId() == null) {
                produtoService.incluirAtomico(produto);  // Persistir produtos novos
            } else {
                produtoService.obterPorID(produto.getId());  // Reatachar produtos desanexados
            }
        }


        if(venda.getOperador().getId() == null) {
            operadorService.incluirAtomico();
        } else {
            operadorService.obterPorID();
        }
  */
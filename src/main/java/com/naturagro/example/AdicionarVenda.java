package com.naturagro.example;

import com.naturagro.models.Funcionario;
import com.naturagro.models.FuncionarioFactory;
import com.naturagro.models.Produto;
import com.naturagro.models.Venda;
import com.naturagro.service.FuncionarioService;
import com.naturagro.service.ProdutoService;
import com.naturagro.service.VendaService;

import java.util.List;

public class AdicionarVenda {
    public static void main(String[] args) {

        //A persistência das vendas no banco é provavelmente a operação mais complexa que teremos
        ProdutoService produtoService = new ProdutoService();
        FuncionarioService funcionarioService = new FuncionarioService();
        VendaService vendaService = new VendaService();

        //Venda é um objeto composto por outros:
        //Por uma Lista de Produto
        //Por um Funcionário
        //Então para persistir uma venda no banco de dados é preciso adicioná-los ao produto Venda



        //Aqui pego um Funcionário do banco de dados
        Funcionario f = funcionarioService.obterPorID(1L);

        System.out.println(f.getNome());

        //Pego uma lista de Produto do banco de dados
        List<Produto> produtos = produtoService.buscarPorPreco(2, 30);

        for (Produto produto : produtos) {
            System.out.println(produto.getNome() + " "+ produto.getPrecoVarejo());
            //Imprimo para conferir os produtos que foram recuperados
        }

        //Objeto do tipo Venda é instanciado
        Venda v = new Venda();
        //Operador atribuido à Venda
        v.setOperador(f);
        //Produtos adicionados à Venda
        v.setProdutos(produtos);

        //Objeto do tipo Venda é persistido
        vendaService.salvarVenda(v);
        //Feito de qualquer outra forma, não funcionará
    }
}

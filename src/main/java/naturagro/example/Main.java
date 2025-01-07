package naturagro.example;

import naturagro.models.Operador;
import naturagro.models.Produto;
import naturagro.service.OperadorService;
import naturagro.service.ProdutoService;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        ProdutoService produtoService = new ProdutoService();
        LocalDate dataEntrada = LocalDate.now();
        LocalDate dataVencimento = LocalDate.now().plusDays(30);



        System.out.println();
        System.out.println(dataEntrada);
        System.out.println(dataVencimento);
        Produto produto = new Produto(1l, "Produto test", "Produto test", "Produto test",dataEntrada, dataVencimento,
                100, 105.5, 110.1);
        //produtoService.incluirAtomico(produto);

        Operador operador = new Operador(1l, "Operador Test", "CPF test");
        OperadorService oS = new OperadorService();
        oS.incluirAtomico(operador);

    }
}
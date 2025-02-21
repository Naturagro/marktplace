package com.naturagro.service;



//import com.itextpdf.kernel.pdf.*;
//import com.itextpdf.layout.Document;
//import com.itextpdf.layout.element.*;
import com.naturagro.DAO.DAO;
import com.naturagro.models.Produto;
import com.naturagro.models.Venda;

import javax.management.Query;
import javax.persistence.TypedQuery;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class VendaService extends DAO<Venda> {

    ProdutoService produtoService = new ProdutoService();
    FuncionarioService funcionarioService = new FuncionarioService();

    public VendaService() {
        super(Venda.class);
    }


    public void salvarVenda(Venda venda) {
        this.abrirT();

        for (Produto produto : venda.getProduto()) {
            if (produto.getId() == null) {
                produtoService.incluirAtomico(produto);
            } else {
                produto = produtoService.obterPorID(produto.getId());
            }
        }

        if (venda.getOperador().getId() == null) {
            funcionarioService.incluirAtomico(venda.getOperador());
        } else {
            venda.setOperador(funcionarioService.obterPorID(venda.getOperador().getId()));
        }

        mesclar(venda);
        fecharT();
//        fechar();

    }

    public List<Object[]> obterItensMaisVendidos() {
        String sql = "SELECT p.id, COUNT(v) AS totalVendas FROM Venda v JOIN v.produto p GROUP BY p.id ORDER BY totalVendas DESC";


        TypedQuery<Object[]> query = getEntityManager().createQuery(sql, Object[].class);
        List<Object[]> resultados = query.getResultList();
        return resultados;
    }

    // Busca vendas dentro de um intervalo de datas
    public List<Venda> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return consultar("Venda.buscarPorPeriodo", "inicio", inicio, "fim", fim);
    }

    // Busca vendas realizadas por um operador específico
    public List<Venda> buscarPorOperador(Long operadorId) {
        return consultar("Venda.buscarPorOperador", "operadorId", operadorId);
    }

    // Busca vendas que contenham um determinado produto
    public List<Venda> buscarPorProduto(Long produtoId) {
        return consultar("Venda.buscarPorProduto", "produtoId", produtoId);
    }

    // Busca vendas que contenham produtos de uma determinada categoria
    public List<Venda> buscarPorCategoria(String categoria) {
        return consultar("Venda.buscarPorCategoria", "categoria", categoria);
    }

    // Conta o total de vendas registradas no sistema
    public Long contarTotalVendas() {
        return consultarUm("Venda.contarTotalVendas").getId();
    }

    // Retorna o valor total das vendas dentro de um determinado período
    public Venda somarValorTotalPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return consultarUm("Venda.somarValorTotalPorPeriodo", "inicio", inicio, "fim", fim);
    }



////gerar relatorio de vendas
//    public void gerarRelatorioDeVendas(String caminhoArquivo) {
//        List<Venda> vendas = obterTodos(); // Busca todas as vendas
//        if (vendas.isEmpty()) {
//            System.out.println("Nenhuma venda encontrada para gerar o relatório.");
//            return;
//        }
//
//        try {
//            PdfWriter writer = new PdfWriter(new FileOutputStream(caminhoArquivo));
//            PdfDocument pdf = new PdfDocument(writer);
//            Document document = new Document(pdf);
//
//            // Título do Relatório
//            document.add(new Paragraph("Relatório de Vendas").setBold().setFontSize(18));
//            document.add(new Paragraph("\n"));
//
//            // Criando a tabela
//            Table table = new Table(4);
//            table.addCell("ID Venda");
//            table.addCell("Data");
//            table.addCell("Operador");
//            table.addCell("Valor Total");
//
//            // Preenchendo a tabela com os dados das vendas
//            for (Venda venda : vendas) {
//                table.addCell(String.valueOf(venda.getId()));
//                table.addCell(venda.getDataCompra().toString());
//                table.addCell(venda.getOperador().getNome());
//                table.addCell("R$ " + String.format("%.2f", venda.getValorTotal()));
//            }
//
//            // Adicionando a tabela ao documento
//            document.add(table);
//            document.close();
//
//            System.out.println("Relatório gerado com sucesso: " + caminhoArquivo);
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}

package com. naturagro. utils;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import com.naturagro.models.Lote;
import com.naturagro.models.Produto;
import com.naturagro.models.Venda;
import com.naturagro.service.LoteService;
import com.naturagro.service.ProdutoService;
import com.naturagro.service.VendaService;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class GeradorRelatorio {

    public static void gerarRelatorioDia(LocalDate data) {
        // Define o caminho da pasta de Downloads
        String caminhoDownloads = Paths.get(System.getProperty("user.home"), "Downloads").toString();

        // Gera um nome de arquivo único
        String nomeArquivo = gerarNomeUnico(caminhoDownloads, "Relatorio", "pdf");
        String caminhoPDF = Paths.get(caminhoDownloads, nomeArquivo).toString();

        // Cria a pasta Downloads se ela não existir
        File pastaDownloads = new File(caminhoDownloads);
        if (!pastaDownloads.exists()) {
            pastaDownloads.mkdirs(); // Cria a pasta e quaisquer pastas pai necessárias
        }


        VendaService vendaService = new VendaService();
        List<Venda> vendas = vendaService.buscarPorPeriodo(data.atStartOfDay(), data.plusDays(1).atStartOfDay());

        LoteService loteService = new LoteService();
        List<Lote> lotes = loteService.consultarLotePorData(LocalDate.from(data.atStartOfDay()));

        try {
            // Cria o PDF no caminho especificado
            PdfWriter writer = new PdfWriter(caminhoPDF);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Adiciona um parágrafo ao PDF
            document.add(new Paragraph("Naturagro LTDA"));
            document.add(new Paragraph("CNPJ: 80.996.492/0001-56"));

            // Obtém a data e hora atuais
            LocalDateTime dataHoraAtual = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String dataHoraFormatada = dataHoraAtual.format(formatter);

            // Adiciona a data e hora ao PDF
            document.add(new Paragraph("Data de emissão: " + dataHoraFormatada));
            document.add(new Paragraph("_____________________________________________________________________________")).setBold();
            document.add(new Paragraph("HISTÓRICO DE VENDAS DO DIA:  " + data));

            if (vendas.isEmpty()) {
                document.add(new Paragraph("Nenhuma venda encontrada para a data selecionada."));
            } else {
                // Criar tabela
                Table table = new Table(3); // Definindo proporções das colunas
                table.setWidth(UnitValue.createPercentValue(100));

                // Cabeçalhos da tabela
                table.addHeaderCell(new Cell().add(new Paragraph("ID")).setBold());
                table.addHeaderCell(new Cell().add(new Paragraph("Data")).setBold());
                table.addHeaderCell(new Cell().add(new Paragraph("Valor (R$)")).setBold());

                for (Venda venda : vendas) {
                    table.addCell(String.valueOf(venda.getId()));
                    table.addCell(venda.getDataCompra().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    table.addCell(String.format("%.2f", venda.getValorTotal()));
                }

                document.add(table);
            }

            document.add(new Paragraph("  "));
            document.add(new Paragraph("HISTÓRICO DE ENTRADA (LOTE) DO DIA:  " + data));

            if (lotes.isEmpty()) {
                document.add(new Paragraph("Nenhuma entrada de lote encontrada para a data selecionada."));
            } else {
                // Criar tabela
                Table table2 = new Table(5); // Definindo proporções das colunas
                table2.setWidth(UnitValue.createPercentValue(100));

                // Cabeçalhos da tabela
                table2.addHeaderCell(new Cell().add(new Paragraph("ID")).setBold());
                table2.addHeaderCell(new Cell().add(new Paragraph("Produto")).setBold());
                table2.addHeaderCell(new Cell().add(new Paragraph("Data Entrada")).setBold());
                table2.addHeaderCell(new Cell().add(new Paragraph("Data Vencimento")).setBold());
                table2.addHeaderCell(new Cell().add(new Paragraph("Qtd.")).setBold());

                for (Lote lote : lotes) {
                    table2.addCell(String.valueOf(lote.getId()));
                    table2.addCell(String.valueOf(lote.getProduto()));
                    table2.addCell(lote.getDataEntrada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    table2.addCell(lote.getDataVencimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    table2.addCell(String.valueOf(lote.getQuantidade()));
                }

                document.add(table2);
            }

            // Fecha o documento
            document.close();

            System.out.println("PDF gerado com sucesso!");
            System.out.println("Salvo em: " + caminhoPDF);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    //todo ajeitar esse metodo se der tempo
    public static void gerarRelatorioVendido(LocalDate data){
        // Define o caminho da pasta de Downloads
        String caminhoDownloads = Paths.get(System.getProperty("user.home"), "Downloads").toString();

        // Gera um nome de arquivo único
        String nomeArquivo = gerarNomeUnico(caminhoDownloads, "Relatorio", "pdf");
        String caminhoPDF = Paths.get(caminhoDownloads, nomeArquivo).toString();

        // Cria a pasta Downloads se ela não existir
        File pastaDownloads = new File(caminhoDownloads);
        if (!pastaDownloads.exists()) {
            pastaDownloads.mkdirs(); // Cria a pasta e quaisquer pastas pai necessárias
        }


        try {
            // Cria o PDF no caminho especificado
            PdfWriter writer = new PdfWriter(caminhoPDF);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Adiciona um parágrafo ao PDF
            document.add(new Paragraph("Naturagro LTDA"));
            document.add(new Paragraph("CNPJ: 80.996.492/0001-56"));

            // Obtém a data e hora atuais
            LocalDateTime dataHoraAtual = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String dataHoraFormatada = dataHoraAtual.format(formatter);

            // Adiciona a data e hora ao PDF
            document.add(new Paragraph("Data de emissão: " + dataHoraFormatada));
            document.add(new Paragraph("_____________________________________________________________________________")).setBold();
            document.add(new Paragraph("PRODUTOS MAIS VENDIDOS NO DIA:  " + data));

            VendaService vendaService = new VendaService();
            ProdutoService produtoService = new ProdutoService();

            Table table = new Table(4);

            table.setWidth(UnitValue.createPercentValue(100));

            // Cabeçalhos da tabela
            table.addHeaderCell(new Cell().add(new Paragraph("ID")).setBold());
            table.addHeaderCell(new Cell().add(new Paragraph("Nome")).setBold());
            table.addHeaderCell(new Cell().add(new Paragraph("Número de Vendas")).setBold());
            table.addHeaderCell(new Cell().add(new Paragraph("Valor Total(R$)")).setBold());

            List<Object[]> resultados = vendaService.obterItensMaisVendidos();

            for (Object[] resultado : resultados) {
                Long produtoId = (Long) resultado[0];
                Long vendas = (Long) resultado[1];

                Produto produto = produtoService.obterPorID(produtoId); // Evita múltiplas chamadas

                double valorTotal = produto.getPreco() * vendas;

                table.addCell(String.valueOf(produtoId));
                table.addCell(produto.getNome());
                table.addCell(String.valueOf(vendas));
                table.addCell(String.valueOf(valorTotal));
            }
            document.add(table);




            // Fecha o documento
            document.close();

            System.out.println("PDF gerado com sucesso!");
            System.out.println("Salvo em: " + caminhoPDF);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static String gerarNomeUnico(String diretorio, String nomeBase, String extensao) {
        String nomeArquivo = nomeBase + "." + extensao; // nome inicial
        File arquivo = new File(diretorio, nomeArquivo);

        int contador = 1;
        while (arquivo.exists()) {
            // se o arquivo já existe, adiciona um número ao nome
            nomeArquivo = nomeBase + "(" + contador + ")." + extensao;
            arquivo = new File(diretorio, nomeArquivo);
            contador++;
        }

        return nomeArquivo;
    }
}
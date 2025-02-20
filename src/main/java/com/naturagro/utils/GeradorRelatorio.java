package com. naturagro. utils;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class GeradorRelatorio {
    public static void main(String[] args) {
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

            // Formata a data e hora
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String dataHoraFormatada = dataHoraAtual.format(formatter);

            // Adiciona a data e hora ao PDF
            document.add(new Paragraph("Data de emissão: " + dataHoraFormatada));
            document.add(new Paragraph("________________________________________________________________________________"));

            //todo: pegar a data passada no front e usá-lá de parametro aqui

            //todo: query que consulta do banco todas as vendas a partir da data escolhida

            //todo: query que consulta do banco todo lote a partir da data escolhida

            // Fecha o documento
            document.close();

            System.out.println("PDF gerado com sucesso!");
            System.out.println("Salvo em: " + caminhoPDF);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static String gerarNomeUnico(String diretorio, String nomeBase, String extensao) {
        String nomeArquivo = nomeBase + "." + extensao; // Nome inicial (ex: "Relatorio.pdf")
        File arquivo = new File(diretorio, nomeArquivo);

        int contador = 1;
        while (arquivo.exists()) {
            // Se o arquivo já existe, adiciona um número ao nome (ex: "Relatorio(1).pdf")
            nomeArquivo = nomeBase + "(" + contador + ")." + extensao;
            arquivo = new File(diretorio, nomeArquivo);
            contador++;
        }

        return nomeArquivo;
    }
}
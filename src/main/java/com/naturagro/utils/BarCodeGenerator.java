package com.naturagro.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.naturagro.models.Produto;
import com.naturagro.service.ProdutoService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BarCodeGenerator {
    private void gerarCodigoBarra(String s) {
        try {

            String data = s;
            String fileName = data + ".png";

            //Path resourcesPath = Paths.get("src", "main", "resources", "images");

            String os = System.getProperty("os.name").toLowerCase();
            Path baseDir;

            if (os.contains("win")) {
                baseDir = Paths.get(System.getProperty("user.home"), "imagens", "naturagro", "barcode");
            } else {
                baseDir = Paths.get(System.getProperty("user.home"), "Documents","naturagro", "barcode");
            }

            if (!baseDir.toFile().exists()) {
                baseDir.toFile().mkdirs();
            }

            Path outputPath = baseDir.resolve(fileName);


            BitMatrix bitMatrix = new MultiFormatWriter().encode(
                    data,
                    BarcodeFormat.CODE_128,
                    300,
                    100
            );

            MatrixToImageWriter.writeToPath(
                    bitMatrix,
                    "PNG",
                    outputPath
            );

            System.out.println("Arquivo salvo em: "+ outputPath.toAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gerarCodigoTodosProdutos() {
        ProdutoService produtoService = new ProdutoService();
        List<Produto> produtos = produtoService.obterTodos();

        for (Produto produto : produtos) {
            gerarCodigoBarra(String.valueOf(produto.getId()));
        }
    }

    public void gerarCodigoProduto(Produto produto) {
        gerarCodigoBarra(String.valueOf(produto.getId()));
    }
}

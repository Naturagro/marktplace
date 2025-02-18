package com.naturagro.payment.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class PostRequest {


    public static final String URL_POST = "http://localhost:8080/pagar";
    public static final String FILE_JSON = "src/main/java/com/naturagro/payment/http/pedido.json";

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();

        try {
            Path filePath = Path.of(FILE_JSON);

            if (!Files.exists(filePath)) {
                throw new IOException("Arquivo JSON não encontrado: " + FILE_JSON);
            }

            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofFile(filePath))  // Lê o JSON do arquivo
                    .timeout(Duration.ofSeconds(10))
                    .uri(URI.create(URL_POST))  // URL do servidor local
                    .header("Content-Type", "application/json")  // Define o tipo do conteúdo como JSON
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)  // Extrai o corpo da resposta
                    .thenAccept(response -> {
                        System.out.println("Resposta da API de pagamento: " + response);
                    })
                    .join();

        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo JSON: " + e.getMessage());
        }
    }
}

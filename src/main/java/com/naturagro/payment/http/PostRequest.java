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
    private static final String ACCESS_TOKEN = "APP_USR-2517486585527936-021909-74d18b5986b5cb2c31df3bdd21cbb7a8-1852011805";
    private static final String URL_POST = "https://api.mercadopago.com/v1/payments";
    private static final String FILE_JSON = "src/main/java/com/naturagro/payment/http/pedido.json";

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();

        try {
            Path filePath = Path.of(FILE_JSON);

            if (!Files.exists(filePath)) {
                throw new IOException("Arquivo JSON não encontrado: " + FILE_JSON);
            }

            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofFile(filePath))
                    .timeout(Duration.ofSeconds(10))
                    .uri(URI.create(URL_POST))
                    .header("Authorization", "Bearer " + ACCESS_TOKEN)
                    .header("Content-Type", "application/json")
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(System.out::println)
                    .join();

        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo JSON: " + e.getMessage());
        }
    }
}

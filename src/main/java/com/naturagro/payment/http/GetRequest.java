package com.naturagro.payment.http;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class GetRequest {
    private static final String ACCESS_TOKEN = "APP_USR-2517486585527936-021909-74d18b5986b5cb2c31df3bdd21cbb7a8-1852011805";
    private static final String PAYMENT_ID = "102895699746";
    private static final String BASE_URL = "https://api.mercadopago.com/v1/payments/";

    public static void consultarPagamento(String paymentId) {
        if (ACCESS_TOKEN == null || ACCESS_TOKEN.isEmpty()) {
            System.err.println("ACCESS_TOKEN não está configurado.");
            return;
        }

        String url = BASE_URL + paymentId;
        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .uri(URI.create(url))
                    .header("Authorization", "Bearer " + ACCESS_TOKEN)
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONObject jsonResponse = new JSONObject(response.body());
                System.out.println("Consulta realizada com sucesso!");
                System.out.println(jsonResponse.toString(4)); // 📑 Formata saída JSON
            } else {
                System.err.println("Erro na consulta. Código: " + response.statusCode());
                System.err.println("Resposta: " + response.body());
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao consultar pagamento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       consultarPagamento("102895699746");
    }
}

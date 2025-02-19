package com.naturagro.payment.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class GetRequest {
    private static final String ACCESS_TOKEN = "APP_USR-2517486585527936-021909-74d18b5986b5cb2c31df3bdd21cbb7a8-1852011805";
    private static final String PAYMENT_ID = "12345";
    private static final String URL_GET = "https://api.mercadopago.com/v1/payments/" + PAYMENT_ID;

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .timeout(Duration.ofSeconds(10))
                .uri(URI.create(URL_GET))
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Código de status: " + response.statusCode());
        System.out.println("Corpo da resposta: " + response.body());
    }
}

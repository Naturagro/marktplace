package com.naturagro.payment.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONObject;

public class PaymentApi {
    private static final String ACCESS_TOKEN = "APP_USR-2517486585527936-021909-74d18b5986b5cb2c31df3bdd21cbb7a8-1852011805";
    private static final int PORT = 8080;
    private static final String BASE_URL = "https://api.mercadopago.com/v1";

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/pagar", new PaymentHandler());
        server.createContext("/consultar", new GetPaymentHandler());
        server.start();

        System.out.println("🚀 API de Pagamento rodando em http://localhost:" + PORT);
    }

    static class PaymentHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!"POST".equals(exchange.getRequestMethod())) {
                sendResponse(exchange, 405, "{\"erro\": \"Método não permitido\"}");
                return;
            }

            String requestBody = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            JSONObject json = new JSONObject(requestBody);

            try {
                HttpClient client = HttpClient.newHttpClient();
                JSONObject paymentData = new JSONObject();
                paymentData.put("transaction_amount", json.getDouble("transaction_amount"));
                paymentData.put("description", json.getString("description"));
                paymentData.put("payment_method_id", json.getString("payment_method_id"));
                paymentData.put("installments", json.getInt("installments"));
                paymentData.put("token", json.getString("token"));

                JSONObject payer = new JSONObject();
                JSONObject payerJson = json.getJSONObject("payer");
                payer.put("email", payerJson.getString("email"));

                paymentData.put("payer", payer);


                String idempotencyKey = "test-key-1234";

                System.out.println("X-Idempotency-Key: " + idempotencyKey); // Debug para verificar

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(BASE_URL + "/payments"))
                        .header("Authorization", "Bearer " + ACCESS_TOKEN)
                        .header("Content-Type", "application/json")
                        .header("X-Idempotency-Key", idempotencyKey)  // ✅ Garantindo que o cabeçalho está aqui
                        .POST(HttpRequest.BodyPublishers.ofString(paymentData.toString()))
                        .build();


                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                sendResponse(exchange, response.statusCode(), response.body());

            } catch (Exception e) {
                sendResponse(exchange, 500, "{\"erro\": \"Erro ao processar pagamento: " + e.getMessage() + "\"}");
            }
        }
    }

    static class GetPaymentHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!"GET".equals(exchange.getRequestMethod())) {
                sendResponse(exchange, 405, "{\"erro\": \"Método não permitido\"}");
                return;
            }

            String query = exchange.getRequestURI().getQuery();
            Map<String, String> params = getQueryParams(query);

            if (!params.containsKey("id")) {
                sendResponse(exchange, 400, "{\"erro\": \"ID do pagamento não fornecido\"}");
                return;
            }

            try {
                String paymentId = params.get("id");

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(BASE_URL + "/payments/" + paymentId))
                        .header("Authorization", "Bearer " + ACCESS_TOKEN)
                        .header("Content-Type", "application/json")
                        .GET()
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                sendResponse(exchange, response.statusCode(), response.body());

            } catch (Exception e) {
                sendResponse(exchange, 500, "{\"erro\": \"Erro ao consultar pagamento: " + e.getMessage() + "\"}");
            }
        }
    }

    private static Map<String, String> getQueryParams(String query) {
        Map<String, String> params = new HashMap<>();
        if (query == null || query.isEmpty()) return params;

        for (String param : query.split("&")) {
            String[] keyValue = param.split("=");
            if (keyValue.length == 2) {
                params.put(keyValue[0], keyValue[1]);
            }
        }
        return params;
    }

    private static void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(statusCode, response.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes(StandardCharsets.UTF_8));
        }
    }
}

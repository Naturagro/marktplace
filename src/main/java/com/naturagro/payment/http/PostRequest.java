package com.naturagro.payment.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.util.UUID;
import org.json.JSONObject;

public class PostRequest {
    private static final String ACCESS_TOKEN = "APP_USR-2517486585527936-021909-74d18b5986b5cb2c31df3bdd21cbb7a8-1852011805";
    private static final String URL_PAYMENT = "https://api.mercadopago.com/v1/payments";
    private static final String URL_TOKEN = "https://api.mercadopago.com/v1/card_tokens";
    private static final String FILE_PATH = "src/main/java/com/naturagro/payment/http/pedido.json";

    public static void realizarPagamento() {
        try {
            if (ACCESS_TOKEN == null || ACCESS_TOKEN.isEmpty()) {
                throw new IllegalStateException("ACCESS_TOKEN não está configurado.");
            }

            String token = gerarToken();
            if (token == null) {
                System.err.println("Erro ao gerar token!");
                return;
            }

            boolean tokenAtualizado = atualizarArquivoPedido(token);
            if (!tokenAtualizado) {
                System.err.println("Erro ao atualizar o arquivo pedido.json.");
                return;
            }

            JSONObject pedido = lerArquivoPedido();
            if (pedido == null) {
                System.err.println("Erro ao ler o arquivo de pedido.");
                return;
            }

            String idempotencyKey = UUID.randomUUID().toString();

            enviarPagamento(pedido.toString(), idempotencyKey);

        } catch (Exception e) {
            System.err.println("Erro ao processar pagamento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static JSONObject lerArquivoPedido() {
        try {
            Path path = Path.of(FILE_PATH);

            if (!Files.exists(path)) {
                System.err.println("Arquivo pedido.json não encontrado!");
                return null;
            }

            String content = Files.readString(path);
            return new JSONObject(content);
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return null;
        }
    }

    // atualiza o pedido.json com o token gerado
    private static boolean atualizarArquivoPedido(String token) {
        try {
            Path path = Path.of(FILE_PATH);

            if (!Files.exists(path)) {
                System.err.println("Arquivo pedido.json não encontrado.");
                return false;
            }

            String content = Files.readString(path);
            JSONObject pedido = new JSONObject(content);

            pedido.put("token", token);

            JSONObject payer = pedido.optJSONObject("payer");
            if (payer == null) {
                payer = new JSONObject();
                pedido.put("payer", payer);
            }
            payer.put("email", "teste@gmail.com");

            Files.writeString(path, pedido.toString(4), StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Arquivo pedido.json atualizado com sucesso!");

            return true;
        } catch (IOException e) {
            System.err.println("Erro ao atualizar o arquivo pedido.json: " + e.getMessage());
            return false;
        }
    }

    // envia o pagamento ao Mercado Pago
    private static void enviarPagamento(String jsonContent, String idempotencyKey) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(jsonContent))
                .timeout(Duration.ofSeconds(10))
                .uri(URI.create(URL_PAYMENT))
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .header("Content-Type", "application/json")
                .header("X-Idempotency-Key", idempotencyKey)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Pagamento realizado!\nResposta formatada:");
            System.out.println(new JSONObject(response.body()).toString(4)); // 📑 Melhor visualização

        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao processar pagamento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // gera um token de cartão no Mercado Pago
    private static String gerarToken() throws IOException, InterruptedException {
        JSONObject cartao = new JSONObject();
        cartao.put("card_number", "4235647728025682");
        cartao.put("expiration_month", 11);
        cartao.put("expiration_year", 2025);
        cartao.put("security_code", "123");

        JSONObject cardholder = new JSONObject();
        cardholder.put("name", "APRO");

        JSONObject identification = new JSONObject();
        identification.put("type", "CPF");
        identification.put("number", "12345678909");

        cardholder.put("identification", identification);
        cartao.put("cardholder", cardholder);

        // Envia requisição para gerar Token
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(cartao.toString()))
                .timeout(Duration.ofSeconds(10))
                .uri(URI.create(URL_TOKEN))
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 201) {
            JSONObject jsonResponse = new JSONObject(response.body());
            if (jsonResponse.has("id")) {
                return jsonResponse.getString("id"); // Retorna o token gerado
            } else {
                System.err.println("Resposta inesperada ao gerar token: " + response.body());
                return null;
            }
        } else {
            System.err.println("Erro ao gerar token: " + response.body());
            return null;
        }
    }

    public static void main(String[] args) {
        realizarPagamento();
    }
}
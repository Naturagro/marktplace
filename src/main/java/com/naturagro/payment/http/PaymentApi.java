package com.naturagro.payment.http;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.InetSocketAddress;
import org.json.JSONObject;
import com.naturagro.models.Pagamento;

public class PaymentApi {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/status", new StatusHandler());

        server.createContext("/pagar", new PaymentHandler());

        server.setExecutor(null);
        server.start();

        System.out.println("servidor iniciado na porta 8080");
    }
}

class StatusHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "API de pagamento funcionando";

        exchange.sendResponseHeaders(200, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}

class PaymentHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody());
            BufferedReader br = new BufferedReader(isr);
            StringBuilder requestBody = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                requestBody.append(line);
            }

            JSONObject responseJson = new JSONObject();

            try {
                JSONObject requestJson = new JSONObject(requestBody.toString());

                String id = requestJson.getString("id");
                String tipo = requestJson.getString("tipo");
                double valor = requestJson.getDouble("valor");

                Pagamento pagamento = new Pagamento(id, tipo, valor);

                responseJson.put("status", "successo");
                responseJson.put("messagem", "Pagamento de " + pagamento.getValor() + " com tipo " + pagamento.getTipo() + " realizado com sucesso.");
                responseJson.put("pagamento", new JSONObject()
                        .put("id", pagamento.getId())
                        .put("tipo", pagamento.getTipo())
                        .put("valor", pagamento.getValor()));

            } catch (Exception e) {
                responseJson.put("status", "erro");
                responseJson.put("messagem", "Erro ao processar o pagamento: " + e.getMessage());
            }

            String response = responseJson.toString();
            exchange.sendResponseHeaders(200, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        } else {
            exchange.sendResponseHeaders(405, -1);
        }
    }
}

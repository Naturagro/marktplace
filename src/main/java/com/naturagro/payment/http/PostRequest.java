package com.naturagro.payment.http;


import java.net.http.HttpClient;


public class PostRequest {

    public static final String URL_POST = "http://httpbin.org/forms/post";
    public static final String FILE_JSON = "/home/jm/IdeaProjects/HttpExample/pedido.json";


    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();



    }

}

package com.emse.spring.faircorp.sync;

import com.emse.spring.faircorp.model.Light;
import com.emse.spring.faircorp.model.Status;
import org.json.JSONObject;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpCallUsername {

    // one instance, reuse
    private final HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private long id;
    private int responsestat;
    private String responsebody;
    private String username;


    private JSONObject json;

    public HttpCallUsername(){
    }


    private String monUrl = "http://192.168.1.131/api/";

    public void sendGet() throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(monUrl))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
        responsestat = response.statusCode();

        // print response body
        responsebody = response.body();

        try {

            JSONObject json = new JSONObject(responsebody);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Votre Json n'est pas bon");
        }
    }

    private void sendPost() throws Exception {

        Map<Object, Object> mondata = new HashMap<>();
        mondata.put("devicetype", "my_hue_app#monserveur");

                HttpRequest request = HttpRequest.newBuilder()
                        .POST(buildFormDataFromMap(mondata))
                        .uri(URI.create(monUrl))
                        .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
        responsestat = response.statusCode();
        // print response body
        responsebody = response.body();

        try {
            JSONObject json = new JSONObject(responsebody);
            username=json.getJSONObject("succes").getString("username");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Votre Json n'est pas bon pour le username");
        }
    }


        private static HttpRequest.BodyPublisher buildFormDataFromMap(Map<Object, Object> mondata) {
            var builder = new StringBuilder();
            for (Map.Entry<Object, Object> entry : mondata.entrySet()) {
                if (builder.length() > 0) {
                    builder.append("&");
                }
                builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
                builder.append("=");
                builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
            }
            System.out.println(builder.toString());
            return HttpRequest.BodyPublishers.ofString(builder.toString());
        }

        String getUsername(){
            return username;
        }
    }
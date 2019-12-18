package com.emse.spring.faircorp.sync;



import com.emse.spring.faircorp.model.Light;
import com.emse.spring.faircorp.model.LightDao;
import com.emse.spring.faircorp.model.Status;
import com.emse.spring.faircorp.model.LightDaoImpl;
import org.json.JSONObject;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpCallJson {

    // one instance, reuse
    private final HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private long id;
    private int responsestat;
    private String responsebody;
    private String username;

    private LightDao lightdao;

    private JSONObject json;

    public HttpCallJson(String username, long id){
        this.username=username;
        this.id=id;
    }


    private String monUrl = "http://192.168.1.131/api/"+username+"/lights/"+Long.toString(id);

    public void sendGet() throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(monUrl))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
        responsestat= response.statusCode();

        // print response body
        responsebody= response.body();

        try {

            JSONObject json=new JSONObject(responsebody);
            String status=json.getJSONObject("state").getString("on");
            Integer bri=json.getJSONObject("state").getInt("bri");
            Integer color=json.getJSONObject("state").getInt("hue");
            Integer sat=json.getJSONObject("state").getInt("sat");
            Status st=Status.OFF;

            if (status=="true"){
                st = Status.ON;
            }
            else if (status=="false"){
                st = Status.OFF;
            }

            Light light=lightdao.getLightById(id);

            light.setBri(bri);
            light.setColour(color);
            light.setStatus(st);
            light.setSat(sat);
        }



        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Votre Json n'est pas bon");
        }
    }


    /*private void sendPostBri() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(buildFormDataFromMap(mondata))
                .uri(URI.create(monUrl))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
        System.out.println(response.statusCode());

        // print response body
        System.out.println(response.body());

    }

    private static HttpRequest.BodyPublisher buildFormDataFromMap(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        System.out.println(builder.toString());
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }*/



}
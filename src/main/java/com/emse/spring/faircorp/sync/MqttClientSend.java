//Ici, on crée un client mqtt sur laquel on poste nos message sur notre topic, celui-ci est lu par l'android et l'arduino

package com.emse.spring.faircorp.sync;

import com.emse.spring.faircorp.model.Status;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class MqttClientSend {

    //On instancie le client avec les bonnes options
    private final int qos = 2;
    private MqttClient client;
    private String montopic;


    private String URI="tcp://max.isasecret.com:1723"; //"ssl://192.168.1.3:1883";

    public MqttClientSend() {
        try {
            MqttClientPersistence persistence = new MemoryPersistence();
            this.client = new MqttClient(URI, MqttClient.generateClientId(), persistence);

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            String password = "Y@_oK2";
            options.setPassword(password.toCharArray());
            String username = "majinfo2019";
            options.setUserName(username);
            client.connect(options);

            //on crée le topic
            this.montopic = "torturesNinjas/server/light/";
            client.subscribe(montopic);
        }
        catch(MqttException e){
            e.printStackTrace();
        }

    }

    //On crée une méthode pour créer un JSON avec les attributs et on l'envoie sous forme de requete MQTT sur notre topic
    public void sendRequest(String idLight,Status status, Integer bri, Integer hue, Integer sat) throws MqttException{
        String st = "OFF";
        if (status == Status.ON) {
            st = "ON";
        } else if (status == Status.OFF){
            st = "OFF";
        }

        JSONObject json = new JSONObject();
        json.append("id", idLight);
        json.append("status", st);
        json.append("bri", bri.toString());
        json.append("hue", bri.toString());
        json.append("sat", bri.toString());

        String JsonString=json.toString();

        MqttMessage message = new MqttMessage(JsonString.getBytes());
        message.setQos(qos);
        this.client.publish(this.montopic, message);

    }

}

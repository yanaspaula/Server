/*Ici est instancié un client MQTT qui est à l'écoute des messages envoyés par l'arduino, la fonction callback est appelé lorsqu'un message
est recu
*/


package com.emse.spring.faircorp.sync;


import com.emse.spring.faircorp.entity.Light;
import com.emse.spring.faircorp.model.LightDao;
import com.emse.spring.faircorp.model.Status;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


@Service
public class MqttClientListenRequest {

    private final int qos = 2;
    private MqttClient client;
    private String URI = "tcp://max.isasecret.com:1723"; //"ssl://192.168.1.3:1883";
    private LightDao lightdao;

    //On instancie le client avec les options
    public MqttClientListenRequest() {
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

            //on redéfinit la méthode callback et ses sousméthodes
            MqttCallback callback = new MqttCallback() {
                @Override
                public void connectionLost(Throwable throwable) {
                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) {

                    // Dans un premier temps, on parse le message
                    String mymessage = mqttMessage.toString();
                    try {
                        JSONObject json = new JSONObject(mymessage);
                        long idlight = json.getLong("id");

                        String status = json.getJSONObject("state").getString("on");
                        Integer bri = json.getJSONObject("state").getInt("bri");
                        Integer color = json.getJSONObject("state").getInt("hue");
                        Integer sat = json.getJSONObject("state").getInt("sat");

                        // Dans un second temps, on appelle les méthodes pour modifier nos attributs

                        Status st = Status.OFF;

                        if (status == "true") {
                            st = Status.ON;
                        } else if (status == "false") {
                            st = Status.OFF;
                        }

                        Light light = lightdao.getLightById(idlight);

                        light.setBri(bri);
                        light.setColour(color);
                        light.setStatus(st);
                        light.setSat(sat);

                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Votre Json n'est pas bon");
                    }
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                }
            };

            client.setCallback(callback);

            String montopic = "torturesNinjas/arduino/light/";
            client.subscribe(montopic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}

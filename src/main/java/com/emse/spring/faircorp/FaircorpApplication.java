/* Ce code constitue notre partie serveur de notre projet de majeur pour le groupe SAFFAF/LEMEE/SOARES DE PAULA,
Il permet de générer un serveur local qui traite les requetes venant de notre page web ou de notre appli android.

Elle utilise grace à spring, une base de donnée qui est modifié selon les requetes recues par l'arduino, celle-ci est donc
constament renouvelé pour être utilisé pas la page web et l'appli android

Le code est assez laborieux, contient des parties théoriques et non implémenté dans le projet en général (concept de building, echange http
avec l'arduino en plus du MQTT, etc...). Il est aussi repris du code que nous avions dévellopé en classé, d'ou la presence de parties
irrévélant au programme global.


Ce package est notre fonction main qui lance l'appli spring
 */

package com.emse.spring.faircorp;

import com.emse.spring.faircorp.sync.MqttClientListenRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//TEST Desactiver les commentaire si le listener ne s'instancie pas
@SpringBootApplication
public class FaircorpApplication implements CommandLineRunner {



	MqttClientListenRequest MQTTListener;


    public static void main(String[] args) {
		SpringApplication.run(FaircorpApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		MqttClientListenRequest MQTTListener=new MqttClientListenRequest();
	}
}

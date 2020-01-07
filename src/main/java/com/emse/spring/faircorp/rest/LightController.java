/*Ce controler est lancé au démarage de l'application (du à sa nature de "controler" selon spring) instancie un objet MQTT et
attend un message, elle contient aussi des méthodes pour modifer la table LIGHT selon les requetes https effectués*/


package com.emse.spring.faircorp.rest;

import com.emse.spring.faircorp.entity.Light;
import com.emse.spring.faircorp.entity.Room;
import com.emse.spring.faircorp.model.*;

import com.emse.spring.faircorp.sync.MqttClientSend;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/lights")
@Transactional
public class LightController {

    // On instancie les topics api/server/light/ et api/arduino/light/ qui sont deux topics ou chacun poste ses messages
    // et lit celui de l'autre
    private MqttClientSend mqttClientSend = new MqttClientSend();

    /*Partie HTTP expérimental, A laisser commenté
    private HttpCallUsername httpUsername=new HttpCallUsername();
    private String username= httpUsername.getUsername();
    */

    @Autowired
    private LightDao lightDao;

    @Autowired
    private RoomDao roomDao;



    //trouve toutes les lights
    @GetMapping
    public List<LightDto> findAll(){
        return lightDao.findAll()
                .stream()
                .map(LightDto::new)
                .collect(Collectors.toList());
    }

    //trouve une light par id
    @GetMapping(path="/{id}")
    public LightDto findById(@PathVariable Long id){
        return lightDao.findById(id).map(light -> new LightDto(light)).orElse(null);
    }

    //Modifie le status
    @PutMapping(path= "/{id}/switch")
    public LightDto switchStatus(@PathVariable Long id){
        Light light=lightDao.findById(id).orElseThrow(IllegalArgumentException::new);
        if (light.getStatus()==Status.ON){
            light.setStatus(Status.OFF);
        }
        else if (light.getStatus()==Status.OFF){
            light.setStatus(Status.ON);
        }
        try {
            mqttClientSend.sendRequest(light.getId().toString(), light.getStatus(), light.getBri(), light.getColour(), light.getSat());
        }
        catch(MqttException e){
            e.printStackTrace();
        }
        return new LightDto(light);
    }

    //Modifie le brightness
    @PutMapping(path= "/{id}/bri/{bri}")
    public LightDto changeBri(@PathVariable Long id, @PathVariable Integer bri){
        Light light=lightDao.findById(id).orElseThrow(IllegalArgumentException::new);
        light.setBri(bri);
        try {

            mqttClientSend.sendRequest(light.getId().toString(), light.getStatus(), light.getBri(), light.getColour(), light.getSat());
        }
        catch(MqttException e){
            e.printStackTrace();
        }
        return new LightDto(light);
    }

    //Modifie la saturation
    @PutMapping(path= "/{id}/sat/{sat}")
    public LightDto changeSat(@PathVariable Long id, @PathVariable Integer sat){
        Light light=lightDao.findById(id).orElseThrow(IllegalArgumentException::new);
        light.setSat(sat);
        try {
            mqttClientSend.sendRequest(light.getId().toString(), light.getStatus(), light.getBri(), light.getColour(), light.getSat());
        }
        catch(MqttException e){
            e.printStackTrace();
        }
        return new LightDto(light);
    }

    //Passe la couleur en rouge
    @PutMapping(path= "/{id}/set-light-red")
    public LightDto setLightRed(@PathVariable Long id){
        Light light=lightDao.findById(id).orElseThrow(IllegalArgumentException::new);
        light.setColour(0);
        try {
            mqttClientSend.sendRequest(light.getId().toString(), light.getStatus(), light.getBri(), light.getColour(), light.getSat());
        }
        catch(MqttException e){
            e.printStackTrace();
        }
        return new LightDto(light);
    }

    //Passe la couleur en vert
    @PutMapping(path= "/{id}/set-light-green")
    public LightDto setLightGreen(@PathVariable Long id){
        Light light=lightDao.findById(id).orElseThrow(IllegalArgumentException::new);
        light.setColour(21845);
        try {
            mqttClientSend.sendRequest(light.getId().toString(), light.getStatus(), light.getBri(), light.getColour(), light.getSat());
        }
        catch(MqttException e){
            e.printStackTrace();
        }
        return new LightDto(light);
    }

    //passe la couleur en blue
    @PutMapping(path= "/{id}/set-light-blue")
    public LightDto setLightBlue(@PathVariable Long id){
        Light light=lightDao.findById(id).orElseThrow(IllegalArgumentException::new);
        light.setColour(43690);
        try {
            mqttClientSend.sendRequest(light.getId().toString(), light.getStatus(), light.getBri(), light.getColour(), light.getSat());
        }
        catch(MqttException e){
            e.printStackTrace();
        }
        return new LightDto(light);
    }


    //Modifie l'id room
    @PutMapping(path= "/{id}/set/{idRoom}")
    public LightDto setRoom(@PathVariable Long id, @PathVariable Long idRoom){
        Light light=lightDao.findById(id).orElseThrow(IllegalArgumentException::new);
        Room room=roomDao.findById(idRoom).orElseThrow(IllegalArgumentException::new);
        light.setRoom(room);
        return new LightDto(light);
    }

    //Cree une lumière
    @PostMapping
    public LightDto create(@RequestBody LightDto dto) {
        Light light = null;
        if (dto.getId() != null) {
            light = lightDao.findById(dto.getId()).orElse(null);
        }

        if (light == null) {
            light = lightDao.save(new Light(dto.getStatus(),dto.getBri(),dto.getColour(),dto.getSat(),roomDao.getOne(dto.getRoomId())));
        } else {
            light.setStatus(dto.getStatus());
            lightDao.save(light);
        }
        return new LightDto(light);
    }

    //suprime une lumiere selon son ID
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        lightDao.deleteById(id);
    }
}



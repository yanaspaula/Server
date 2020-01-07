package com.emse.spring.faircorp.rest;

import com.emse.spring.faircorp.entity.Light;
import com.emse.spring.faircorp.entity.Room;
import com.emse.spring.faircorp.model.*;
import com.emse.spring.faircorp.sync.MqttClientSend;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms")
@Transactional
public class RoomController {

    MqttClientSend mqttClientSend=new MqttClientSend();

    @Autowired
    RoomDao roomDao;

    @Autowired
    private LightDao lightDao;

    @Autowired
    private BuildingDao buildingDao;



    @GetMapping
    public List<RoomDto> findAll() {
        return roomDao.findAll()
                .stream()
                .map(RoomDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{room_id}")
    public RoomDto findById(@PathVariable Long room_id) {
        return roomDao.findById(room_id).map(room -> new RoomDto(room)).orElse(null);
    }

    @PutMapping(path = "/{room_id}/switchLights")
    public List<LightDto> switchStatus(@PathVariable Long room_id) {
        Room room = roomDao.findById(room_id).orElseThrow(IllegalArgumentException::new);
        List<Light> lights = room.getLights();
        List<LightDto> lightdtos = new ArrayList<>();
        for (int i = 0; i < lights.size(); i++) {
            Light light = lights.get(i);
            light.setStatus(light.getStatus() == Status.ON ? Status.OFF : Status.ON);
            try {
                mqttClientSend.sendRequest(light.getId().toString(), light.getStatus(), light.getBri(), light.getColour(), light.getSat());
            }
            catch(MqttException e){
                e.printStackTrace();
            }
            lightdtos.add(new LightDto(light));
        }
        return lightdtos;
    }

    @PostMapping
    public RoomDto create(@RequestBody RoomDto dto) {
        Room room = null;
        if (dto.getId() != null) {
            room = roomDao.findById(dto.getId()).orElse(null);
        }

        if (room == null) {
            List<LightDto> lightDtos=dto.getLights();
            List<Light> lights=new ArrayList<>();
            for (int i=0;i<lightDtos.size();i++){
                lights.add(getLight(lightDtos.get(i)));
            }
            room = roomDao.save(new Room(dto.getId(),dto.getName(),dto.getFloor(),buildingDao.getOne(dto.getBuildingId()),lights));
        } else {
            room.setFloor(dto.getFloor());
            roomDao.save(room);
        }

        return new RoomDto(room);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        roomDao.deleteById(id);
    }

    public Light getLight(LightDto dto){
        return lightDao.getOne(dto.getId());
    }


}

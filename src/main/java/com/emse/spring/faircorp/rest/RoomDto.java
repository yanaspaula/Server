package com.emse.spring.faircorp.rest;

import com.emse.spring.faircorp.model.*;
import com.emse.spring.faircorp.model.RoomDao;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

public class RoomDto {

    private Long id;

    private String name;

    private Integer floor;

    private Long buildingId;

    private List<LightDto> lights;

    public RoomDto(){}

    public RoomDto(Room room){
        this.id=room.getId();
        this.name=room.getName();
        this.floor=room.getFloor();
        this.buildingId=room.getBuilding().getId();
        List<Light> roomLights=room.getLights();
        this.lights=new ArrayList<LightDto>();
        for (int i=0;i<roomLights.size();i++){
            lights.add(new LightDto(roomLights.get(i)));
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getFloor() {
        return floor;
    }

    public Long getBuildingId(){
        return buildingId;
    }

    public List<LightDto> getLights() {
        return lights;
    }
}

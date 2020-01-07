
package com.emse.spring.faircorp.rest;

import com.emse.spring.faircorp.entity.Building;
import com.emse.spring.faircorp.entity.Room;

import java.util.List;

public class BuildingDto {

    private Long id;
    private String name;
    private List<Room> rooms;

    public BuildingDto(){}

    public BuildingDto(Building building){
        this.id=building.getId();
        this.name=building.getName();
        this.rooms=building.getRooms();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}

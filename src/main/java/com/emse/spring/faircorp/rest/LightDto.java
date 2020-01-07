//LightDTO est une classe parallèle à LIGHT, c'est elle qui subit les modifications gérées par le controleur

package com.emse.spring.faircorp.rest;

import com.emse.spring.faircorp.entity.Light;
import com.emse.spring.faircorp.model.*;

public class LightDto {

    private Long id;
    private Status status;
    private Integer bri;
    private Integer colour;
    private Integer sat;
    private Long roomId;

    public LightDto() {
    }

    public LightDto(Light light) {
        this.id = light.getId();
        this.status = light.getStatus();
        this.bri=light.getBri();
        this.colour=light.getColour();
        this.sat=light.getSat();
        this.roomId=light.getRoomId();
    }

    public Long getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public Integer getBri(){return bri;}

    public Integer getColour(){return colour;}

    public Integer getSat(){return sat;}

    public Long getRoomId(){
        return roomId;
    }

}
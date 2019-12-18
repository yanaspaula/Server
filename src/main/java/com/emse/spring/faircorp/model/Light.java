package com.emse.spring.faircorp.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;



@Entity
@Table
public class Light {

    @Id
    @GeneratedValue
    @NotNull
    private Long id;



    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @NotNull
    private Integer bri;

    private Integer colour;

    private Integer sat;

    @ManyToOne
    private Room room;

    public Light(){}



    public Light(Status status, Integer bri, Integer colour, Integer sat, Room room) {
        this.status = status;
        if ((bri.intValue()>=1)&&(bri.intValue()<=265)){
            this.bri=bri;
        }
        else{
            throw new IllegalArgumentException("La luminosité n'existe pas");
        }
        if ((colour.intValue()>=0)&&(colour.intValue()<=65535)){
            this.colour=colour;
        }
        else{
            throw new IllegalArgumentException("La couleur n'existe pas");
        }
        if ((sat.intValue()>=0)&&(colour.intValue()<=255)){
            this.sat=sat;
        }
        else{
            throw new IllegalArgumentException("La saturation n'existe pas");
        }
        this.room=room;
    }



    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }




    public Integer getBri(){return bri;}

    public void setBri(Integer bri){
        if ((bri.intValue()>=0)&&(bri.intValue()<=255)){
            this.bri=bri;
        }
        else{
            throw new IllegalArgumentException("La luminosité n'existe pas");
        }
    }

    public Integer getColour(){return colour;}

    public void setColour(Integer colour) {
        if ((colour.intValue()>=0)&&(colour.intValue()<=65535)) {
            this.colour = colour;
        }
        else {
            throw new IllegalArgumentException("La couleur n'existe pas");
        }
    }

    public Integer getSat(){return sat;}

    public void setSat(Integer sat){
        if ((sat.intValue()>=0)&&(colour.intValue()<=255)){
            this.sat=sat;
        }
        else{
            throw new IllegalArgumentException("La saturation n'existe pas");
        }
    }

    public Status getStatus() {
        return status;
    }



    public void setStatus(Status status) {
        this.status = status;
    }

    public Room getRoom(){
        return this.room;
    }

    public Long getRoomId(){return this.room.getId();}
}

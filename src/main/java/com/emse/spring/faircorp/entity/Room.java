package com.emse.spring.faircorp.entity;

import com.emse.spring.faircorp.entity.Building;
import com.emse.spring.faircorp.entity.Light;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;



@Entity
@Table
public class Room {

    @Id
    @NotNull
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer floor;

    @OneToMany(mappedBy="room")
    private List<Light> lights;

    @ManyToOne
    private Building building;

    public Room(){}

    public Room(@NotNull Long id, @NotNull String name, @NotNull Integer level, @NotNull Building building){
        this.id=id;
        this.name=name;
        this.floor=level;
        this.building=building;
    }

    public Room(@NotNull Long id,@NotNull String name, @NotNull Integer level,@NotNull Building building,@NotNull List<Light> l){
        this.id=id;
        this.name=name;
        this.floor=level;
        this.building=building;
        for (int i=0;i<l.size();i++){
            this.lights.add(l.get(i));
        }
    }

    public void addLight(Light light){
        if (light.getRoom()==this){
            this.lights.add(light);
        }
    }

    public Long getId(){return id;}

    public String getName(){return name; }

    public Integer getFloor(){return floor;}

    public Building getBuilding(){return building;}

    public List<Light> getLights(){
        return lights;
    }

    public void setFloor(Integer floor) { this.floor=floor;};
    }


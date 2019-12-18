package com.emse.spring.faircorp.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Building {

    @Id
    @NotNull
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "building")
    private List<Room> rooms;

    public Building(){}

    public Building(Long id,String name){
        this.id=id;
        this.name=name;
    }


    public String getName(){return name;}

    public Long getId(){return id;}

    public List<Room> getRooms(){return rooms;}
}

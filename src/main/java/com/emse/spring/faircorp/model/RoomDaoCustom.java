package com.emse.spring.faircorp.model;

import com.emse.spring.faircorp.entity.Light;
import com.emse.spring.faircorp.entity.Room;

import java.util.List;

public interface RoomDaoCustom {
    Room findRoom(String name);

    List<Light> findLights(Long id);


}

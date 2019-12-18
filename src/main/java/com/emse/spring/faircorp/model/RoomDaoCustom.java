package com.emse.spring.faircorp.model;

import java.util.List;

public interface RoomDaoCustom {
    Room findRoom(String name);

    List<Light> findLights(Long id);


}

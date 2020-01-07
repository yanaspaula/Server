package com.emse.spring.faircorp.model;

import com.emse.spring.faircorp.entity.Light;

import java.util.List;

//On définis des méthodes pour notre entité
public interface LightDaoCustom {
    List<Light> findOnLights();

    List<Light> findOffLights();

    Light getLightById(Long id);
}

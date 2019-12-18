package com.emse.spring.faircorp.model;

import java.util.List;

public interface LightDaoCustom {
    List<Light> findOnLights();

    List<Light> findOffLights();

    Light getLightById(Long id);
}

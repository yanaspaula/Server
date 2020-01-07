package com.emse.spring.faircorp.model;

import com.emse.spring.faircorp.entity.Light;

import java.util.List;

public interface BuildingDaoCustom {


    List<Light> findLights(Long id);
}

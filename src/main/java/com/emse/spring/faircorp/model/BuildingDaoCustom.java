package com.emse.spring.faircorp.model;

import java.util.List;

public interface BuildingDaoCustom {


    List<Light> findLights(Long id);
}

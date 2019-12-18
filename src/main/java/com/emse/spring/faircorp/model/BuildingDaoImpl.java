package com.emse.spring.faircorp.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

public class BuildingDaoImpl implements BuildingDaoCustom{


    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Light> findLights(Long id) {

        String jpql = "select la from Lights la join Room lb on la.room_id=lb.id where lb.building_id= :id";
        return em.createQuery(jpql, Light.class)
                .setParameter("id",id)
                .getResultList();
    }
}



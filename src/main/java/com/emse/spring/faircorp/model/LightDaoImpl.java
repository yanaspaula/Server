package com.emse.spring.faircorp.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class LightDaoImpl implements LightDaoCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Light> findOnLights() {
        String jpql = "select lt from Light lt where lt.status = :value";
        return em.createQuery(jpql, Light.class)
                .setParameter("value", Status.ON)
                .getResultList();
    }



    @Override
    public List<Light> findOffLights() {
        String jpql = "select lt from Light lt where lt.status = :value";
        return em.createQuery(jpql, Light.class)
                .setParameter("value", Status.OFF)
                .getResultList();
    }

    @Override
    public Light getLightById(Long id) {
        String jpql="select lt from Light lt where lt.id = :value";
        return em.createQuery(jpql,Light.class)
                .setParameter("value",id)
                .getSingleResult();
    }

}

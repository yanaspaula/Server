package com.emse.spring.faircorp.model;

import com.emse.spring.faircorp.entity.Light;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/*On redéfinit les méthodes de notre interface*/
public class LightDaoImpl implements LightDaoCustom {
    @PersistenceContext
    private EntityManager em;

    // methode permettant de chercher des lights allumés
    @Override
    public List<Light> findOnLights() {
        String jpql = "select lt from Light lt where lt.status = :value";
        return em.createQuery(jpql, Light.class)
                .setParameter("value", Status.ON)
                .getResultList();
    }


    // methode permettant de chercher des lights éteintes
    @Override
    public List<Light> findOffLights() {
        String jpql = "select lt from Light lt where lt.status = :value";
        return em.createQuery(jpql, Light.class)
                .setParameter("value", Status.OFF)
                .getResultList();
    }

    // methode permettant de chercher un light selon son ID
    @Override
    public Light getLightById(Long id) {
        String jpql="select lt from Light lt where lt.id = :value";
        return em.createQuery(jpql,Light.class)
                .setParameter("value",id)
                .getSingleResult();
    }

}

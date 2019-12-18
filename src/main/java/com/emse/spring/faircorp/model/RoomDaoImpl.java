package com.emse.spring.faircorp.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class RoomDaoImpl implements RoomDaoCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Room findRoom(String name) {
        String jpql="select lt from Room where lt.name=:name";
        return em.createQuery(jpql,Room.class)
                .setParameter("name",name)
                .getSingleResult();
}


    @Override
    public List<Light> findLights(Long id) {
        String jpql = "select lt from Light lt where lt.room_id =:room_id";
        return em.createQuery(jpql, Light.class)
                .setParameter("room_id", id)
                .getResultList();
    }
}

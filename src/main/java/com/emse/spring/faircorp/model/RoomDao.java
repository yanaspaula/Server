package com.emse.spring.faircorp.model;

import com.emse.spring.faircorp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RoomDao extends JpaRepository<Room,Long>, RoomDaoCustom {
}


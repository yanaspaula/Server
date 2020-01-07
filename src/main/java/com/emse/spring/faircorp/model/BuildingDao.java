package com.emse.spring.faircorp.model;

import com.emse.spring.faircorp.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingDao extends JpaRepository<Building,Long>,BuildingDaoCustom {
}

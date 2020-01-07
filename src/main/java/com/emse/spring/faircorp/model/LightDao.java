package com.emse.spring.faircorp.model;

//On crée une interface pour implémenter des méthodes de JpaRepository, utile pour pour modifier notre table de manière dynamique
import com.emse.spring.faircorp.entity.Light;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LightDao extends JpaRepository<Light,Long>,LightDaoCustom {
}

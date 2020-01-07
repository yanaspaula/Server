//Ici on effectue des tests sur notre table, et pour l'entité LIGHT en particulier

package com.emse.spring.faircorp.model;

import com.emse.spring.faircorp.model.LightDao;
import com.emse.spring.faircorp.model.Status;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class LightDaoCustomImplTest {

    @Autowired
    private LightDao lightDao;

    @Test
    //on vérifie que l'on est capable de détecter les lumières allumées
    public void shouldFindOnLights() {
        Assertions.assertThat(lightDao.findOnLights())
                .hasSize(1)
                .extracting("id", "status")
                .containsExactly(Tuple.tuple(-1L, Status.ON));
    }

    //idem pour les éteintes
    @Test
    public void shouldFindOffLights() {
        Assertions.assertThat(lightDao.findOffLights())
                .hasSize(1)
                .extracting("id", "status")
                .containsExactly(Tuple.tuple(-1L, Status.OFF));
    }






}

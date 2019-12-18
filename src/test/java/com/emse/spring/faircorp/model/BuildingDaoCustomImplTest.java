package com.emse.spring.faircorp.model;

import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BuildingDaoCustomImplTest {

    @Autowired
    private BuildingDao buildingDao;




    @Test
    public void shouldFindLights(){
        Assertions.assertThat(buildingDao.findLights(1L))
                .hasSize(2);

    }
}

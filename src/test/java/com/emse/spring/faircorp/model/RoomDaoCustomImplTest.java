package com.emse.spring.faircorp.model;

import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class
RoomDaoCustomImplTest {

    @Autowired
    private RoomDao roomDao;

    @Test
    public void shouldFindRoom(){
        Assertions.assertThat(roomDao.findRoom("Room1"))
                .extracting("id","floor")
                .containsExactly(Tuple.tuple(-10,1));
    }

    @Test
    public void shouldFindLights(){
        Assertions.assertThat(roomDao.findLights(-10L))
                .hasSize(2);

    }
}

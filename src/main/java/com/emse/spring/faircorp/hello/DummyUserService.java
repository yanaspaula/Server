package com.emse.spring.faircorp.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DummyUserService implements UserService {

    @Autowired
    GreetingService greetingService;
    @Override
    public void greetAll() {
        ArrayList<String> users=new ArrayList<>();
        users.add("Jérémy");
        users.add("Yana");
        users.add("Saïd");
        users.forEach(u -> greetingService.greet(u) );

    }
}

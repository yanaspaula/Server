package com.emse.spring.faircorp.rest;

import com.emse.spring.faircorp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lights")
@Transactional
public class LightController {

    @Autowired
    private LightDao lightDao;

    @Autowired
    private RoomDao roomDao;

    @GetMapping
    public List<LightDto> findAll(){
        return lightDao.findAll()
                .stream()
                .map(LightDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(path="/{id}")
    public LightDto findById(@PathVariable Long id){
        return lightDao.findById(id).map(light -> new LightDto(light)).orElse(null);
    }


    @PutMapping(path= "/{id}/switch")
    public LightDto switchStatus(@PathVariable Long id){
        Light light=lightDao.findById(id).orElseThrow(IllegalArgumentException::new);
        light.setStatus(light.getStatus() == Status.ON ? Status.OFF: Status.ON);
        return new LightDto(light);
    }

    @PostMapping
    public LightDto create(@RequestBody LightDto dto) {
        Light light = null;
        if (dto.getId() != null) {
            light = lightDao.findById(dto.getId()).orElse(null);
        }

        if (light == null) {
            light = lightDao.save(new Light(dto.getStatus(),dto.getBri(),dto.getColour(),dto.getSat(),roomDao.getOne(dto.getRoomId())));
        } else {
            light.setStatus(dto.getStatus());
            lightDao.save(light);
        }

        return new LightDto(light);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        lightDao.deleteById(id);
    }
}

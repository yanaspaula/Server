package com.emse.spring.faircorp.rest;

import com.emse.spring.faircorp.entity.Building;
import com.emse.spring.faircorp.entity.Room;
import com.emse.spring.faircorp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/buildings")
@Transactional
public class BuildingController {

    @Autowired
    BuildingDao buildingDao;

    @Autowired
    RoomDao roomDao;

    @Autowired
    private LightDao lightDao;

    @GetMapping
    public List<RoomDto> findAll() {
        List<BuildingDto> buildingDtos=buildingDao.findAll()
                .stream()
                .map(BuildingDto::new)
                .collect(Collectors.toList());
        List<RoomDto> roomDtos=new ArrayList<>();
        for (int i=0;i<buildingDtos.size();i++){
            List<Room> rooms=buildingDtos.get(i).getRooms();
            for (int j=0;j<rooms.size();j++){
                roomDtos.add(new RoomDto(rooms.get(j)));
            }
        }
        return roomDtos;
    }

    @GetMapping(path = "/{building_id}")
    public BuildingDto findById(@PathVariable Long room_id) {
        return buildingDao.findById(room_id).map(building -> new BuildingDto(building)).orElse(null);
    }



    @PostMapping
    public BuildingDto create(@RequestBody BuildingDto dto) {
        Building building = null;
        if (dto.getId() != null) {
            building = buildingDao.findById(dto.getId()).orElse(null);
        }

        if (building == null) {
            building = buildingDao.save(new Building(dto.getId(),dto.getName()));
        } else {
            buildingDao.save(building);
        }

        return new BuildingDto(building);
    }

    @DeleteMapping(path = "/{building_id}")
    public void delete(@PathVariable Long building_id) {
        buildingDao.deleteById(building_id);
    }

}

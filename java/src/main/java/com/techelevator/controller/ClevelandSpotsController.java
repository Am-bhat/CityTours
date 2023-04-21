package com.techelevator.controller;


import com.techelevator.dao.ClevelandSpotsDao;
import com.techelevator.model.ThingToDoDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class ClevelandSpotsController {

    private ClevelandSpotsDao clevelandSpotsDao;

    public ClevelandSpotsController(ClevelandSpotsDao clevelandSpotsDao){
        this.clevelandSpotsDao = clevelandSpotsDao;
    }

    @RequestMapping(value = "/thingsToDo", method = RequestMethod.GET)
    public List<ThingToDoDto> getAllThingsToDo() {
        return clevelandSpotsDao.findAll();
    }

    @RequestMapping(value ="/thingsToDo/freeAdmission", method = RequestMethod.GET)
    public List<ThingToDoDto> findFreeActivities(){
            return clevelandSpotsDao.findFreeActivities();
    }

    @RequestMapping(value = "thingsToDo/{id}", method = RequestMethod.GET)
    public ThingToDoDto findThingToDoById(@PathVariable int id) {
        ThingToDoDto thingToDoDto = clevelandSpotsDao.getThingToDoById(id);

        if(thingToDoDto == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No landmark with this location ID");
        } else {
            return thingToDoDto;
        }
    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "thingsToDo/create", method = RequestMethod.POST)
    public boolean createThingToDO(@RequestBody ThingToDoDto thingToDoDto){
        return clevelandSpotsDao.create(thingToDoDto);
    }
}



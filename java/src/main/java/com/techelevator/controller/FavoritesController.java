package com.techelevator.controller;

import com.techelevator.dao.ClevelandSpotsDao;
import com.techelevator.dao.FavoritesDao;
import com.techelevator.model.ThingToDoDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class FavoritesController {

        private FavoritesDao favoritesDao;

        public FavoritesController(FavoritesDao favoritesDao){
            this.favoritesDao = favoritesDao;
        }

        @RequestMapping(value = "/favorites", method = RequestMethod.GET)
        public List<ThingToDoDto> getAllThingsToDo() {
            return favoritesDao.findAll();
        }

        @RequestMapping(value ="/favorites/freeAdmission", method = RequestMethod.GET)
        public List<ThingToDoDto> findFreeActivities(){
            return favoritesDao.findFreeActivities();
        }

        @RequestMapping(value = "favorites/{id}", method = RequestMethod.GET)
        public ThingToDoDto findThingToDoById(@PathVariable int id) {
            ThingToDoDto thingToDoDto = favoritesDao.getThingToDoById(id);

            if(thingToDoDto == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No landmark with this location ID");
            } else {
                return thingToDoDto;
            }
        }
        @ResponseStatus(HttpStatus.CREATED)
        @RequestMapping(path = "favorites/{userId}/create", method = RequestMethod.POST)
        public boolean createThingToDO(@RequestBody ThingToDoDto thingToDoDto, @PathVariable int userId){
            return favoritesDao.create(thingToDoDto, userId);
        }


        @RequestMapping(path = "favorites/{userId}/{favoriteName}/delete", method = RequestMethod.POST)
        public void deleteFavorites() {

        }
    }




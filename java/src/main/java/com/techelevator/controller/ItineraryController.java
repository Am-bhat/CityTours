package com.techelevator.controller;

import com.techelevator.dao.ItineraryDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Itinerary;
import com.techelevator.model.ThingToDoDto;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@CrossOrigin
public class ItineraryController {

    @Autowired
    private ItineraryDao itineraryDao;
    private UserDao userDao;



    @RequestMapping(path = "/itinerary", method = RequestMethod.GET)
    public List<Itinerary> getAllItineraries() {
        return itineraryDao.getAllItineraries();
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/itinerary/{id}/create", method = RequestMethod.POST)
    public Integer create(@RequestBody Itinerary itinerary, @PathVariable int id){
        return itineraryDao.create(itinerary, id);
    }

    @RequestMapping(path = "/itinerary/{id}/delete", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        itineraryDao.delete(id);
    }

    @RequestMapping(path = "/itinerary/{userId}/{itineraryId}", method = RequestMethod.DELETE)
    public void deleteItineraryByUserAndItineraryId(@PathVariable int userId, @PathVariable int itineraryId){
        itineraryDao.deleteByUserAndItineraryId(userId, itineraryId);
    }

    @RequestMapping(path = "/itinerary/{userId}/all", method = RequestMethod.GET)
    public List<Itinerary> getAllItinerariesByUserId(@PathVariable int userId) {
        return itineraryDao.getAllItinerariesByUserId(userId);
    }
    @RequestMapping(path = "/itinerary/{itineraryId}", method = RequestMethod.GET)
    public Itinerary getItineraryByItineraryId(@PathVariable int itineraryId) {
        return itineraryDao.getItineraryByItineraryId(itineraryId);
    }

    @RequestMapping(path = "/itinerary/{userId}/{itineraryId}", method = RequestMethod.GET)
    public Itinerary getItineraryByItineraryIdAndUserId(@PathVariable int userId, @PathVariable int itineraryId) {
        return itineraryDao.getItineraryByUserIdAndItineraryId(userId, itineraryId);
    }

//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(path = "/itinerary/{userId}/{itineraryId}/{landmarkId}", method = RequestMethod.POST)
//    public void create(@PathVariable int userId, @PathVariable int itineraryId, @PathVariable int landmarkId) {
//        itineraryDao.addThingToDoToItinerary(itineraryId, landmarkId);
//    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/itinerary/{userId}/{itineraryId}", method = RequestMethod.POST)
    public void saveItineraryDestinationIds(@PathVariable int userId, @PathVariable int itineraryId, @RequestBody int[] destinationIds){
        itineraryDao.addThingToDoToItinerary(itineraryId, destinationIds);
    }


    @RequestMapping(path = "itinerary/{userId}/{itineraryId}/thingToDo", method = RequestMethod.GET)
    public List<ThingToDoDto> getAllActivitiesByUserIdAndItineraryId(@PathVariable int userId, @PathVariable int itineraryId) {
        return itineraryDao.getAllItineraryActivitiesByUserAndItineraryId(userId, itineraryId);
    }

    @RequestMapping(path = "itinerary/checkId/{userId}", method = RequestMethod.GET)
    public boolean checkAllItineraryIds(@PathVariable int userId){
        return itineraryDao.checkAllItineraryIds(userId);
    }

    @RequestMapping(path = "itinerary/{userId}/{itineraryId}/update", method = RequestMethod.PUT)
    public void updateItinerary(@PathVariable int userId, @PathVariable int itineraryId, @RequestBody Itinerary itinerary) {
        itineraryDao.updateItinerary(userId, itineraryId, itinerary);
    }
}





//    post POJO





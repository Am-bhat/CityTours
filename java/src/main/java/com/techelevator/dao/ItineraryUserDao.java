package com.techelevator.dao;

import com.techelevator.model.Itinerary;
import com.techelevator.model.ItineraryUser;

import java.util.List;

public interface ItineraryUserDao {

    List<ItineraryUser> getAll();

    ItineraryUser create();

    List<Itinerary> getItineraryByUserId(int userId);

    void delete(int userId, int itineraryId);

}

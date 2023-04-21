package com.techelevator.dao;

import com.techelevator.model.Itinerary;
import com.techelevator.model.ThingToDoDto;
import com.techelevator.model.User;

import java.util.List;

public interface ItineraryDao {

    List<Itinerary> getAllItineraries();

    Integer create(Itinerary itinerary, int userId);

    void delete(int Id);

    void deleteByUserAndItineraryId(int userId, int itineraryId);

    List<ThingToDoDto> getAllItineraryActivitiesByUserAndItineraryId(int userId, int itineraryId);

    List<Itinerary> getAllItinerariesByUserId(int userId);

    Itinerary getItineraryByItineraryId(int itineraryId);

    void addThingToDoToItinerary(int itineraryId, int[] destinationIds);

    Itinerary getItineraryByUserIdAndItineraryId(int itineraryId, int userId);

    void updateItineraryTitle(String title, int itineraryId);

    boolean checkAllItineraryIds(int userId);

    void updateItinerary(int userId, int itineraryId, Itinerary itinerary);
}

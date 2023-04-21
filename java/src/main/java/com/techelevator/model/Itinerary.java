package com.techelevator.model;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Itinerary {
    
    private int itineraryId;
    private String itineraryTitle;
    private Date itineraryDate;
    private Time startingTime;
    private List<ThingToDoDto> itineraryItems;


    public Itinerary() {
    }


    public Itinerary(int itineraryId, String itineraryTitle) {
        this.itineraryId = itineraryId;
        this.itineraryTitle = itineraryTitle;
    }

    public Itinerary(int itineraryId) {
        this.itineraryId = itineraryId;
    }

    public Itinerary(int itineraryId, String itineraryTitle, Date itineraryDate, Time startingTime, List<ThingToDoDto> itineraryItems) {
        this.itineraryId = itineraryId;
        this.itineraryTitle = itineraryTitle;
        this.itineraryDate = itineraryDate;
        this.startingTime = startingTime;
        this.itineraryItems = itineraryItems;
    }

    public Itinerary(int itineraryId, String itineraryTitle, Date itineraryDate, Time startingTime) {
        this.itineraryId = itineraryId;
        this.itineraryTitle = itineraryTitle;
        this.itineraryDate = itineraryDate;
        this.startingTime = startingTime;
    }

    public Itinerary(String itineraryTitle, Date itineraryDate, Time startingTime) {
        this.itineraryTitle = itineraryTitle;
        this.itineraryDate = itineraryDate;
        this.startingTime = startingTime;
    }

    public int getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(int itineraryId) {
        this.itineraryId = itineraryId;
    }

    public String getItineraryTitle() {
        return itineraryTitle;
    }

    public void setItineraryTitle(String itineraryTitle) {
        this.itineraryTitle = itineraryTitle;
    }

    public Date getItineraryDate() {
        return itineraryDate;
    }

    public void setItineraryDate(Date itineraryDate) {
        this.itineraryDate = itineraryDate;
    }

    public Time getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Time startingTime) {
        this.startingTime = startingTime;
    }

    public List<ThingToDoDto> getItineraryItems() {
        return itineraryItems;
    }

    public void setItineraryItems(List<ThingToDoDto> itineraryItems) {
        this.itineraryItems = itineraryItems;
    }
}


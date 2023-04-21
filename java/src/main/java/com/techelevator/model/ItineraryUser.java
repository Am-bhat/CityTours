package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItineraryUser {

    @JsonProperty
    public int itineraryId;
    @JsonProperty
    public int userId;

    public ItineraryUser() {

    }

    public ItineraryUser(int itineraryId, int userId) {
        this.itineraryId = itineraryId;
        this.userId = userId;
    }

    public int getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(int itineraryId) {
        this.itineraryId = itineraryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

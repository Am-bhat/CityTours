package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ItineraryLandmark {


    @JsonProperty
    public int itineraryId;
    @JsonProperty
    public int landmarkId;

    public ItineraryLandmark() {}


    public ItineraryLandmark(int itineraryId, int landmarkId) {
        this.itineraryId = itineraryId;
        this.landmarkId = landmarkId;
    }

    public int getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(int itineraryId) {
        this.itineraryId = itineraryId;
    }

    public int getLandmarkId() {
        return landmarkId;
    }

    public void setLandmarkId(int landmarkId) {
        this.landmarkId = landmarkId;
    }

}
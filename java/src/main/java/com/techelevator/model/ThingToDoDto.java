package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.sql.Time;


public class ThingToDoDto {
    private int id;
    @JsonProperty
    private String imageUrl;
    @JsonProperty
    private String description;
    @JsonProperty
    private String name;
    @JsonProperty
    private String type;
    @JsonProperty
    private BigDecimal latitude;
    @JsonProperty
    private BigDecimal longitude;
    @JsonProperty
    private Time mondayOpen;
    @JsonProperty
    private Time mondayClose;
    @JsonProperty
    private Time tuesdayOpen;
    @JsonProperty
    private Time tuesdayClose;
    @JsonProperty
    private Time wednesdayOpen;
    @JsonProperty
    private Time wednesdayClose;
    @JsonProperty
    private Time thursdayOpen;
    @JsonProperty
    private Time thursdayClose;
    @JsonProperty
    private Time fridayOpen;
    @JsonProperty
    private Time fridayClose;
    @JsonProperty
    private Time saturdayOpen;
    @JsonProperty
    private Time saturdayClose;
    @JsonProperty
    private Time sundayOpen;
    @JsonProperty
    private Time sundayClose;
    @JsonProperty
    private boolean kidFriendly;
    @JsonProperty
    private boolean freeAdmission;
    @JsonProperty
    private String restaurantType;
    @JsonProperty
    private boolean outdoor;
    @JsonProperty
    private double landmarkRating;

    public ThingToDoDto() {
    }

    public ThingToDoDto(String imageUrl, String description, String name, String type, BigDecimal latitude, BigDecimal longitude, Time mondayOpen,
                        Time mondayClose, Time tuesdayOpen, Time tuesdayClose, Time wednesdayOpen, Time wednesdayClose, Time thursdayOpen, Time thursdayClose,
                        Time fridayOpen, Time fridayClose, Time saturdayOpen, Time saturdayClose, Time sundayOpen, Time sundayClose, boolean kidFriendly,
                        boolean freeAdmission, String restaurantType, boolean outdoor, double landmarkRating) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.description = description;
        this.name = name;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.mondayOpen = mondayOpen;
        this.mondayClose = mondayClose;
        this.tuesdayOpen = tuesdayOpen;
        this.tuesdayClose = tuesdayClose;
        this.wednesdayOpen = wednesdayOpen;
        this.wednesdayClose = wednesdayClose;
        this.thursdayOpen = thursdayOpen;
        this.thursdayClose = thursdayClose;
        this.fridayOpen = fridayOpen;
        this.fridayClose = fridayClose;
        this.saturdayOpen = saturdayOpen;
        this.saturdayClose = saturdayClose;
        this.sundayOpen = sundayOpen;
        this.sundayClose = sundayClose;
        this.kidFriendly = kidFriendly;
        this.freeAdmission = freeAdmission;
        this.restaurantType = restaurantType;
        this.outdoor = outdoor;
        this.landmarkRating = landmarkRating;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }


    public boolean isKidFriendly() {
        return kidFriendly;
    }

    public void setKidFriendly(boolean kidFriendly) {
        this.kidFriendly = kidFriendly;
    }

    public boolean isFreeAdmission() {
        return freeAdmission;
    }

    public void setFreeAdmission(boolean freeAdmission) {
        this.freeAdmission = freeAdmission;
    }

    public String getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(String restaurantType) {
        this.restaurantType = restaurantType;
    }

    public boolean isOutdoor() {
        return outdoor;
    }

    public void setOutdoor(boolean outdoor) {
        this.outdoor = outdoor;
    }

    public double getLandmarkRating() {
        return landmarkRating;
    }

    public void setLandmarkRating(double landmarkRating) {
        this.landmarkRating = landmarkRating;
    }

    public Time getMondayOpen() {
        return mondayOpen;
    }

    public void setMondayOpen(Time mondayOpen) {
        this.mondayOpen = mondayOpen;
    }

    public Time getMondayClose() {
        return mondayClose;
    }

    public void setMondayClose(Time mondayClose) {
        this.mondayClose = mondayClose;
    }

    public Time getTuesdayOpen() {
        return tuesdayOpen;
    }

    public void setTuesdayOpen(Time tuesdayOpen) {
        this.tuesdayOpen = tuesdayOpen;
    }

    public Time getTuesdayClose() {
        return tuesdayClose;
    }

    public void setTuesdayClose(Time tuesdayClose) {
        this.tuesdayClose = tuesdayClose;
    }

    public Time getWednesdayOpen() {
        return wednesdayOpen;
    }

    public void setWednesdayOpen(Time wednesdayOpen) {
        this.wednesdayOpen = wednesdayOpen;
    }

    public Time getWednesdayClose() {
        return wednesdayClose;
    }

    public void setWednesdayClose(Time wednesdayClose) {
        this.wednesdayClose = wednesdayClose;
    }

    public Time getThursdayOpen() {
        return thursdayOpen;
    }

    public void setThursdayOpen(Time thursdayOpen) {
        this.thursdayOpen = thursdayOpen;
    }

    public Time getThursdayClose() {
        return thursdayClose;
    }

    public void setThursdayClose(Time thursdayClose) {
        this.thursdayClose = thursdayClose;
    }

    public Time getFridayOpen() {
        return fridayOpen;
    }

    public void setFridayOpen(Time fridayOpen) {
        this.fridayOpen = fridayOpen;
    }

    public Time getFridayClose() {
        return fridayClose;
    }

    public void setFridayClose(Time fridayClose) {
        this.fridayClose = fridayClose;
    }

    public Time getSaturdayOpen() {
        return saturdayOpen;
    }

    public void setSaturdayOpen(Time saturdayOpen) {
        this.saturdayOpen = saturdayOpen;
    }

    public Time getSaturdayClose() {
        return saturdayClose;
    }

    public void setSaturdayClose(Time saturdayClose) {
        this.saturdayClose = saturdayClose;
    }

    public Time getSundayOpen() {
        return sundayOpen;
    }

    public void setSundayOpen(Time sundayOpen) {
        this.sundayOpen = sundayOpen;
    }

    public Time getSundayClose() {
        return sundayClose;
    }

    public void setSundayClose(Time sundayClose) {
        this.sundayClose = sundayClose;
    }
}

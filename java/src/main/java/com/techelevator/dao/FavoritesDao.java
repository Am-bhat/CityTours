package com.techelevator.dao;

import com.techelevator.model.ThingToDoDto;

import java.util.List;

public interface FavoritesDao {

        List<ThingToDoDto> findAll();

        List<ThingToDoDto> findFreeActivities();

        ThingToDoDto getThingToDoById(int id);

        boolean create(ThingToDoDto thingToDoDto, int userId);

        void deleteFavorite(String favoriteName, int userId);

    }



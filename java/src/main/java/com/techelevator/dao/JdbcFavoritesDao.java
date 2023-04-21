package com.techelevator.dao;

import com.techelevator.model.ThingToDoDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcFavoritesDao implements FavoritesDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcFavoritesDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<ThingToDoDto> findAll(){

        List<ThingToDoDto> thingsToDo = new ArrayList<>();
        String sql = "Select * from favorites;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            ThingToDoDto thingToDoDto = mapRowToThingToDo(results);
            thingsToDo.add(thingToDoDto);
        }

        return thingsToDo;

    }
    @Override
    public List<ThingToDoDto> findFreeActivities(){
        List<ThingToDoDto> thingsToDo = new ArrayList<>();
        String sql = "select * from favorites WHERE admission = true;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            ThingToDoDto thingToDoDto = mapRowToThingToDo(results);
            thingsToDo.add(thingToDoDto);
        }

        return thingsToDo;
    }
    @Override
    public ThingToDoDto getThingToDoById(int id){
        ThingToDoDto thingToDoDto = null;
        String sql = "select * from favorites WHERE landmark_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        if (results.next()) {
            ThingToDoDto thingToDoDto2 = mapRowToThingToDo(results);
            thingToDoDto = thingToDoDto2;
        }
        return thingToDoDto;
    }

    @Override
    public void deleteFavorite(String favoriteName, int userId){
        String sql = "delete from favorites USING favorites_user WHERE favorites_user.favorites_id = favorites.favorites_id AND favorites_user.user_id = ? AND favorites.landmark_name = ?;";

        jdbcTemplate.update(sql, userId, favoriteName);

    }

    @Override
    public boolean create(ThingToDoDto thingToDoDto, int userId){
        String sql = "INSERT INTO favorites (landmark_img_url, landmark_description, landmark_name, landmark_type, landmark_latitude, landmark_longitude," +
                "monday_open, monday_close, tuesday_open, tuesday_close, wednesday_open, wednesday_close, thursday_open, thursday_close, friday_open, friday_close," +
                "saturday_open, saturday_close, sunday_open, sunday_close, kid_friendly, admission, restaurant_type, is_outdoor, landmark_rating) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING favorites_id;";
        Integer results = jdbcTemplate.queryForObject(sql, Integer.class, thingToDoDto.getImageUrl(), thingToDoDto.getDescription(), thingToDoDto.getName(), thingToDoDto.getType(),
                thingToDoDto.getLatitude(), thingToDoDto.getLongitude(), thingToDoDto.getMondayOpen(), thingToDoDto.getMondayClose(),
                thingToDoDto.getTuesdayOpen(), thingToDoDto.getTuesdayClose(), thingToDoDto.getWednesdayOpen(), thingToDoDto.getWednesdayClose(), thingToDoDto.getThursdayOpen(),
                thingToDoDto.getThursdayClose(), thingToDoDto.getFridayOpen(), thingToDoDto.getFridayClose(), thingToDoDto.getSaturdayOpen(), thingToDoDto.getSaturdayClose(),
                thingToDoDto.getSundayOpen(), thingToDoDto.getSundayClose(), thingToDoDto.isKidFriendly(), thingToDoDto.isFreeAdmission(), thingToDoDto.getRestaurantType(),
                thingToDoDto.isOutdoor(), thingToDoDto.getLandmarkRating());

        if(results != null) {

            String sql2 = "INSERT INTO favorites_user (favorites_id, user_id) VALUES (?, ?)";
            jdbcTemplate.update(sql2, results, userId);
            return true;

        }
        else {
            return false;
        }
    }

    public ThingToDoDto mapRowToThingToDo(SqlRowSet rs) {
        ThingToDoDto thingToDoDto = new ThingToDoDto();
        thingToDoDto.setId(rs.getInt("favorites_id"));
        thingToDoDto.setImageUrl(rs.getString("landmark_img_url"));
        thingToDoDto.setDescription(rs.getString("landmark_description"));
        thingToDoDto.setName(rs.getString("landmark_name"));
        thingToDoDto.setType(rs.getString("landmark_type"));
        thingToDoDto.setLatitude(rs.getBigDecimal("landmark_latitude"));
        thingToDoDto.setLongitude(rs.getBigDecimal("landmark_longitude"));
        thingToDoDto.setMondayOpen(rs.getTime("monday_open"));
        thingToDoDto.setMondayClose(rs.getTime("monday_close"));
        thingToDoDto.setTuesdayOpen(rs.getTime("tuesday_open"));
        thingToDoDto.setTuesdayClose(rs.getTime("tuesday_close"));
        thingToDoDto.setWednesdayOpen(rs.getTime("wednesday_open"));
        thingToDoDto.setWednesdayClose(rs.getTime("wednesday_close"));
        thingToDoDto.setThursdayOpen(rs.getTime("thursday_open"));
        thingToDoDto.setThursdayClose(rs.getTime("thursday_close"));
        thingToDoDto.setFridayOpen(rs.getTime("friday_open"));
        thingToDoDto.setFridayClose(rs.getTime("friday_close"));
        thingToDoDto.setSaturdayOpen(rs.getTime("saturday_open"));
        thingToDoDto.setSaturdayClose(rs.getTime("saturday_close"));
        thingToDoDto.setSundayOpen(rs.getTime("sunday_open"));
        thingToDoDto.setSundayClose(rs.getTime("sunday_close"));
        thingToDoDto.setKidFriendly(rs.getBoolean("kid_friendly"));
        thingToDoDto.setFreeAdmission(rs.getBoolean("admission"));
        thingToDoDto.setRestaurantType(rs.getString("restaurant_type"));
        thingToDoDto.setOutdoor(rs.getBoolean("is_outdoor"));
        thingToDoDto.setLandmarkRating(rs.getDouble("landmark_rating"));
        return thingToDoDto;
    }

}


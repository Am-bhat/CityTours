package com.techelevator.dao;

import com.techelevator.model.Itinerary;
import com.techelevator.dao.ItineraryDao;
import com.techelevator.model.ItineraryLandmark;
import com.techelevator.model.ThingToDoDto;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcItineraryDao implements ItineraryDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcItineraryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Itinerary> getAllItineraries() {

        List<Itinerary> itineraries = new ArrayList<>();
        String sql = "Select * from itinerary;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Itinerary itinerary = mapRowToItinerary(results);
            itineraries.add(itinerary);
        }

        return itineraries;

    }

    @Override
    public List<ThingToDoDto> getAllItineraryActivitiesByUserAndItineraryId(int userId, int itineraryId) {

        JdbcClevelandSpotsDao jdbcClevelandSpotsDao = new JdbcClevelandSpotsDao(jdbcTemplate);

        List<ThingToDoDto> thingToDoDtos = new ArrayList<>();
        String sql = "select * from landmark JOIN itinerary_landmark ON itinerary_landmark.landmark_id = landmark.landmark_id  JOIN itinerary ON itinerary.itinerary_id = itinerary_landmark.itinerary_id JOIN itinerary_user ON itinerary.itinerary_id = itinerary_user.itinerary_id WHERE user_id = ? AND itinerary.itinerary_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, itineraryId);

        while (results.next()) {
            ThingToDoDto thingToDoDto = jdbcClevelandSpotsDao.mapRowToThingToDo(results);
            thingToDoDtos.add(thingToDoDto);
        }

        return thingToDoDtos;
    }

    @Override
    public void addThingToDoToItinerary(int itineraryId, int[] destinationIds) {

        String sql = "INSERT INTO itinerary_landmark (itinerary_id, landmark_id) VALUES (?, ?) RETURNING landmark_id";

        Integer landmarkId = 0;

        for (int i = 0; i < destinationIds.length; i++) {
            landmarkId = jdbcTemplate.queryForObject(sql, Integer.class, itineraryId, destinationIds[i]);
            if (landmarkId == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The landmark could not be found");
        }
    }
}

    @Override
    public Itinerary getItineraryByItineraryId(int itineraryId) {

        Itinerary itinerary = null;
        String sql = "select * from itinerary WHERE itinerary_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, itineraryId);

        if (results.next()) {
            itinerary = mapRowToItinerary(results);
        }

        return itinerary;

    }

    @Override
    public List<Itinerary> getAllItinerariesByUserId(int userId) {

        List<Itinerary> itineraries = new ArrayList<>();
        String sql = "select * from itinerary JOIN itinerary_user ON itinerary_user.itinerary_id = itinerary.itinerary_id where user_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        while (results.next()) {
            Itinerary itinerary = mapRowToItinerary(results);
            itineraries.add(itinerary);
        }

        return itineraries;

    }

    //    TODO proper itinerary ID not updating
    @Override
    public Integer create(Itinerary itinerary, int userId) {
        String sql = "INSERT into itinerary (itinerary_title, itinerary_date, starting_time) VALUES (?, ?, ?) RETURNING itinerary_id;";
//                "JOIN itinerary_user ON itinerary_user.itinerary_id = itinerary_user.itinerary_id;" +
        Integer returningItineraryId = jdbcTemplate.queryForObject(sql, Integer.class, itinerary.getItineraryTitle(), itinerary.getItineraryDate(), itinerary.getStartingTime());

        String sql2 = "INSERT INTO itinerary_user (itinerary_id, user_id) VALUES (?, ?);";

        jdbcTemplate.update(sql2, returningItineraryId, userId);

        if (returningItineraryId == 0) {
            return 0;
        } else {
            return returningItineraryId;
        }
    }


    @Override
    public void delete(int id) {
        String sql = "DELETE from itinerary WHERE itinerary_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void deleteByUserAndItineraryId(int userId, int itineraryId) {

        String sql = "DELETE FROM itinerary USING itinerary_user WHERE itinerary_user.itinerary_id = itinerary.itinerary_id AND itinerary_user.user_id = ? AND itinerary_user.itinerary_id = ?;";

        jdbcTemplate.update(sql, userId, itineraryId);
    }


    @Override
    public Itinerary getItineraryByUserIdAndItineraryId(int itineraryId, int userId) {

        Itinerary itinerary = null;

        String sql = "select * from itinerary JOIN itinerary_user ON itinerary.itinerary_id = itinerary_user.itinerary_id WHERE user_id = ? AND itinerary.itinerary_id = ?;";

        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql, itineraryId, userId);

        if (sqlRowSet.next()) {
            itinerary = mapRowToItinerary(sqlRowSet);
        }

        return itinerary;

    }

    @Override
    public void updateItineraryTitle(String title, int itineraryId) {

        String sql = "UPDATE itinerary SET itinerary_title = '?' WHERE itinerary_id = ?;";

        Integer results = jdbcTemplate.update(sql, title, itineraryId);

    }
    @Override
    public boolean checkAllItineraryIds(int userId){

        String sql = "select itinerary_id FROM itinerary WHERE itinerary_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        if (results.next()) {
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public void updateItinerary(int userId, int itineraryId, Itinerary itinerary){

        String sql = "UPDATE itinerary SET itinerary_title = ?, itinerary_date = ?, starting_time = ? WHERE itinerary_id = ?;";

        jdbcTemplate.update(sql, itinerary.getItineraryTitle(), itinerary.getItineraryDate(), itinerary.getStartingTime(), itineraryId);
    }



    private Itinerary mapRowToItinerary(SqlRowSet rs) {
        Itinerary itinerary = new Itinerary();
        itinerary.setItineraryId(rs.getInt("itinerary_id"));
        itinerary.setItineraryTitle(rs.getString("itinerary_title"));
        itinerary.setItineraryDate(rs.getDate("itinerary_date"));
        itinerary.setStartingTime(rs.getTime("starting_time"));
        return itinerary;
    }

    private ItineraryLandmark mapRowToItineraryLandmark(SqlRowSet rs) {
        ItineraryLandmark itineraryLandmark = new ItineraryLandmark();
        itineraryLandmark.setItineraryId(rs.getInt("itinerary_id"));
        itineraryLandmark.setLandmarkId(rs.getInt("landmark_id"));
        return itineraryLandmark;
    }
}

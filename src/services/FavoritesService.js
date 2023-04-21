import axios from 'axios';




const http = axios.create({
  baseURL: "http://localhost:9000"
});

export default {

  getItineraryActivitiesByUserAndItinerary(userId, itineraryId) {
    return http.get(`/itinerary/${userId}/${itineraryId}/thingToDo`);
  },

  getItinerariesByItineraryId(itineraryId) {
    
    return http.get(`/itinerary/${itineraryId}/all`)
  },

  saveFavoriteToDatabase(destination, userId) {
    return http.post(`/favorites/${userId}/create`, destination)
  }
}
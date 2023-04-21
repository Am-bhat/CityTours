import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:9000"
});

export default {
  getAllItineraries() {
      return http.get('/itinerary');
  },
  getItinerariesByItineraryId(itineraryId) {
    return http.get(`itinerary/${itineraryId}/all`)
  },
  getAllItinerariesByUserId(userId) {
    return http.get(`/itinerary/${userId}/all`)
  },
    getItineraryByUserAndItinerary(userId, itineraryId) {
      return http.get(`/itinerary/${userId}/${itineraryId}`);
    },

// The following two methods should be used together. Save Itinerary returns an int
  saveItineraryInfo(userId, itinerary) {
    return http.post(`/itinerary/${userId}/create`, itinerary)
  },

  updateItineraryInfo(userId, itineraryId, itinerary) {
    return http.put(`/itinerary/${userId}/${itineraryId}/update`, itinerary)
  },

  saveItineraryDestinations(userId, itineraryId, arrayOfLandmarkIds){
    return http.post(`/itinerary/${userId}/${itineraryId}/`, arrayOfLandmarkIds)
  },
  checkItineraryIds(itineraryId) {
    return http.get(`/itinerary/checkId/${itineraryId}/`)
  },
  deleteItinerary(userId, itineraryId) {
    return http.delete(`/itinerary/${userId}/${itineraryId}`)
  },
  getAllItineraryActivities(userId, itineraryId) {
    return http.get(`/itinerary/${userId}/${itineraryId}/thingToDo`)
  }
}
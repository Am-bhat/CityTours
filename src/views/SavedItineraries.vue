<template>
  <div id="adding">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
    <div id="route">
    </div>
    <br>
    <br>
    <h1>Saved Itineraries</h1>
    <div class="itinerary-container" v-for="itinerary in itineraries" :key="itinerary.itineraryId" v-on:click="goToItinerary(itinerary.itineraryId)">
      <div class="itinerary-header">
        <h2 class="itinerary-title"><i id="element" class="fas fa-map-pin"></i> {{ itinerary.itineraryTitle }}</h2>
        <p class="itinerary-date"><i class="far fa-calendar-alt"></i> {{ formatDate(itinerary.itineraryDate) }}</p>
      </div>
    </div>
    <div class="itinerary-container add-container" v-on:click="goToAddItinerary()">
      <h2 class="add-container-text"><i class="fas fa-plus"></i> Create a new itinerary</h2>
    </div>
  </div>
</template>

<script>
import ItineraryService from '../services/ItineraryService.js';

export default {
  name: 'saved-itinerary',
  data() {
    return {
      itineraries: [],
      destinations: [],
      showButton: true,

      defaultItinerary: {
        itineraryTitle: " ",
        itineraryDate: "2023-01-01",
        startingTime: "10:00:00"
}
    }
  },
  methods: {
    goToItinerary(itineraryId) {
      let userId = this.$store.state.user.id
      this.$router.push({ name: 'build itinerary', params: { userId: userId, itineraryId: itineraryId}})
    },
      goToAddItinerary() {
    // let userId = this.$store.state.user.id
        let itineraryId = ItineraryService.saveItineraryInfo(this.$store.state.user.id, this.defaultItinerary);
        this.$router.push(`/itinerary/${itineraryId}`)

        ItineraryService.saveItineraryInfo(this.$store.state.user.id, this.defaultItinerary)
        .then(response => {
          let itineraryId = response.data;
          this.goToItinerary(itineraryId)
        })

        
// itineraryService.getAllItineraryActivities(userId, itineraryId)
//                 .then(response => {
//                 this.destinations = response.data;
//                 this.$store.state.currentItineraryDestinations = this.destinations
//         })
//         .catch(error => {
//           console.log(error);
//         });


        // saveItinerary() {
        //     if (itineraryService.checkItineraryIds(this.$route.params.itineraryId)) {
        //         return null;
        //     } else {
        //     let itineraryId = itineraryService.saveItineraryInfo(this.$store.state.user.id, this.$store.state.currentItineraryInfo);
            
        //     itineraryService.saveItineraryDestinations(
        //         this.$store.state.user.id, itineraryId, this.$store.state.currentItineraryDestinations);
        //     }
        // }
  },
    formatDate(date) {
      const options = { year: 'numeric', month: 'long', day: 'numeric' };
      return new Date(date).toLocaleDateString('en-US', options);
    },
  //   formatTime(time) {
  //   const [hour, minute] = time.split(':');
  //   return `${hour}:${minute}`;
  // }
  },
  created() {
    let userId = this.$store.state.user.id
    ItineraryService.getAllItinerariesByUserId(userId)
      .then(response => {
        console.log(response.data)
        this.itineraries = response.data;
      })
      .catch(error => {
        console.log(error);
      });
  }
}
</script>

<style>
#adding {
  background-image: url(../../assets/CleTOursArUs.jpg) ;
  background-attachment: scroll;
  background-position: center;
  background-size: cover;
  height: 100vh;
  width: 100vw;
  display: flex;
  flex-direction: column;
  align-items: left;
  overflow: auto;
  
}

#route {
  margin-top: 3%;
  font-size: 18px;
  color: white;
  text-align: center;
}

h1 {
  margin-left: 13%
}
/* .itinerary-container {
  border: 1px solid #ccc;
  margin-bottom: 20px;
  padding: 10px;
  width: 80%;
  max-width: 600px;
  background-color: white;
  border-radius: 10px;
  text-align: center;
  margin-left: 3%;
} */

.itinerary-title {
  margin-top: 0;
  margin-bottom: 0.5rem;
  font-size: 24px;
}

.itinerary-date {
  margin-top: 0;
  margin-bottom: 0;
  font-size: 16px;
  color: #999;
}
.itinerary-container {
  /* border: 1px solid #ccc; */
  margin-bottom: 20px;
  padding: 10px;
  width: 80%;
  max-width: 560px;
  background-color: #5f9cff;
  border-radius: 10px;
  text-align: center;
  margin-left: 3%;
  transition: transform 0.2s ease-in-out;

}

.itinerary-container:hover {
  background-color: #eee;
  cursor: pointer;
  transform: translateY(-3px);
}

.itinerary-header {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  flex-direction: column;
}

.itinerary-title {
  margin-top: 0;
  margin-bottom: 0.5rem;
  font-size: 24px;
  display: flex;
  align-items: center;
}

.itinerary-title i {
  margin-right: 10px;
}

.itinerary-date {
  margin-top: 0;
  margin-bottom: 0;
  font-size: 16px;
  color: black;
  display: flex;
  align-items: center;
}

.itinerary-date i {
  margin-right: 10px;
}
#element {
    color: rgba(226, 43, 43, 0.74);
    text-shadow: 1px 1px 1px #ccc;
    font-size: .90em;
}
</style>
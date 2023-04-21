<template>
  <div>
    <div class='filterButtons'>
      <button 
        id='entertainmentAndCulture'
        v-on:click='toggleEntertainmentAndCultureFilter()'
        v-bind:class="{ tag:!entertainmentAndCultureFilter, tagSelected: entertainmentAndCultureFilter }">
        Entertainment & Culture
      </button>
      <button 
        id='parks'
        v-on:click='toggleParksFilter()'
        v-bind:class="{ tag:!parksFilter, tagSelected: parksFilter }">
        Parks
      </button>
      <button
        id='restaurants'
        v-on:click='toggleRestaurantsFilter()'
        v-bind:class="{ tag:!restaurantsFilter, tagSelected: restaurantsFilter }">
        Restaurants
      </button>
      <button
        id='outdoor'
        v-on:click='toggleOutdoorFilter()'
        v-bind:class="{ tag:!outdoorFilter, tagSelected: outdoorFilter }">
        Outdoor
      </button>
      <button
        id='kid-friendly'
        v-on:click='toggleKidFriendlyFilter()'
        v-bind:class="{ tag:!kidFriendlyFilter, tagSelected: kidFriendlyFilter }">
        Kid-Friendly
      </button>
      <button
        id='free'
        v-on:click='toggleFreeFilter()'
        v-bind:class="{ tag:!freeFilter, tagSelected: freeFilter }">
        Free
      </button>
      <button
      id='reset'
      v-on:click='toggleResetFilter()'
      v-bind:class="{tag:!resetFilter, tagSelected: resetFilter }"> RESET FILTERS
      </button>
    </div>
    <br>
    <form class='filters'>
      <b>Filter By Keyword:</b>
      <i>&nbsp;&nbsp;</i>
      <input name="keyword" type="text" placeholder="search" v-model='keyword'>
    </form>
    <p>{{ filteredDestinations.length }} results</p>
    <br>
    <div class="list">
      <destination-card v-for="destination in filteredDestinations" v-bind:key="destination.id" v-bind:id="destination.id" />
    </div>
  </div>
</template>

<script>
import destinationCard from "../components/DestinationCard.vue";
import destinationsService from '../services/DestinationsService';

export default {
  components: {
    destinationCard
  },
  data () {
    return {
      destinations: [],
      keyword: '',
      distance: null,
      rating: null,
      timeOpen: null,
      hourOpen: null,
      entertainmentAndCultureFilter: false,
      parksFilter: false,
      restaurantsFilter: false,
      outdoorFilter: false,
      kidFriendlyFilter: false,
      freeFilter: false,
      // resetFilter:false,
    }
  },
  created() {
    destinationsService.getDestinations().then((response) => {
      this.destinations = response.data;
    });
  },
  computed: {
    filteredDestinations() {
      let filteredArray = this.destinations;
      filteredArray = filteredArray.sort((a, b) => (a.name > b.name) ? 1 : -1)
      filteredArray = filteredArray.filter(d => {
        return (d.name.toLowerCase().includes(this.keyword.toLowerCase())) ||
          (d.type.toLowerCase()).includes(this.keyword.toLowerCase())
      });
      if (this.entertainmentAndCultureFilter) {
        filteredArray = filteredArray.filter(d => {
          return (d.type==='Museum' || d.type==='Market' || d.type==='Garden' || 
                  d.type==='Aquarium' || d.type==='Monument' || d.type==='Cultural Center' || 
                  d.type==='Entertainment' || d.type==='Music Venue' || d.type==='Zoo' || 
                  d.type==='Stadium' || d.type==='Historical Neighborhood' || d.type==='Cultural Hub');
        });
      }
      if (this.parksFilter) {
        filteredArray = filteredArray.filter(d => {
          return (d.type==='Park');
        });
      }
      if (this.restaurantsFilter) {
        filteredArray = filteredArray.filter(d => {
          return (d.type==='Restaurant');
        });
      }
      if (this.outdoorFilter) {
        filteredArray = filteredArray.filter(d => {
          return (d.outdoor);
        });
      }
      if (this.kidFriendlyFilter) {
        filteredArray = filteredArray.filter(d => {
          return (d.kidFriendly);
        });
      }
      if (this.freeFilter) {
        filteredArray = filteredArray.filter(d => {
          return (!d.freeAdmission && d.type!=='Restaurant');
        });
      }
      // if (this.resetFilter) {
      //   filteredArray = filteredArray.filter(d => {
      //     return (d.type!=='Restaurant');

      //   });
      // }
      return filteredArray;
    }
  },
  methods: {
    getDistance(lat1, lon1, lat2, lon2) {
      var R = 6371; // Radius of the earth in km
      var dLat = this.deg2rad(lat2-lat1);  // deg2rad below
      var dLon = this.deg2rad(lon2-lon1); 
      var a = 
          Math.sin(dLat/2) * Math.sin(dLat/2) +
          Math.cos(this.deg2rad(lat1)) * Math.cos(this.deg2rad(lat2)) * 
          Math.sin(dLon/2) * Math.sin(dLon/2)
      ; 
      var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
      var d = R * c; // Distance in km
      return (d*0.621371).toFixed(1); //Distance in mi to one decimal place
    },
    deg2rad(deg) {
      return deg * (Math.PI/180)
    },
    distanceCalc(d) {
      return this.getDistance(d.latitude, d.longitude, this.$store.state.currentLatitude, this.$store.state.currentLongitude)
    },
    toggleEntertainmentAndCultureFilter () {
      this.entertainmentAndCultureFilter = !this.entertainmentAndCultureFilter
    },
    toggleParksFilter () {
      this.parksFilter = !this.parksFilter
    },
    toggleRestaurantsFilter () {
      this.restaurantsFilter = !this.restaurantsFilter
    },
    toggleOutdoorFilter () {
      this.outdoorFilter = !this.outdoorFilter
    },
    toggleKidFriendlyFilter () {
      this.kidFriendlyFilter = !this.kidFriendlyFilter
    },
    toggleFreeFilter () {
      this.freeFilter = !this.freeFilter
    },
    toggleEntertainmentActive () {
      this.entertainmentActive = !this.entertainmentActive;
    },
    toggleResetFilter () {
       this.keyword = '';
      this.distance = null;
      this.rating = null;
      this.timeOpen = null;
      this.hourOpen = null;
      this.entertainmentAndCultureFilter = false;
      this.parksFilter = false;
      this.restaurantsFilter = false;
      this.outdoorFilter = false;
      this.kidFriendlyFilter = false;
      this.freeFilter = false;
      this.resetFilter = true;
  },
    compare( a, b ) {
      if ( a.name < b.name ){
        return -1;
      }
      if ( a.name > b.name ){
        return 1;
      }
      return 0;
    }
  }
  };
</script>

<style>

.list {
    display: flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
}

.filters {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
}

#distanceFilter {
  width: 70px;
}

#dayFilter {
  width: 70px;
}

#ratingFilter {
  width: 70px;
}

#restaurants {
    background-color: brown;
}

#entertainmentAndCulture {
    background-color: lightseagreen;
}

#parks {
    background-color: darkolivegreen;
}

#free {
    background-color: gray;
}
#reset{
  background-color: black;
}

</style>
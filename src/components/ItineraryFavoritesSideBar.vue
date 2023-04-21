<template>
  <div class="favorites-sidebar">
    <h2>My Favorites</h2>
    <itinerary-favorites-side-bar-list id="sidebarFavs"/>
    <button
      id='backToExplore'
      v-on:click='backToExplore()'
      class="tag">
      Back to Explore
    </button>
  </div>
</template>

<script>
import itineraryFavoritesSideBarList from '../components/ItineraryFavoritesSideBarList.vue'

export default {
  components: { itineraryFavoritesSideBarList },
  name: "ItineraryFavoritesSideBar",
  data() {
    return {
      favorites: [],
    };
  },
  methods: {
    clearFavorites() {
      this.$store.commit('CLEAR_FAVORITES');
      location.reload();
    },
    backToExplore() {
      this.$router.push({ name: 'explore' } );
    }
  }
};
</script>

<style>

.favorites-sidebar {
  display: flex;
  flex-direction: column;
  position: fixed;
  top: 30px;
  right: 0;
  width: 200px;
  height: 100%;
  background-color: #f5f5f5;
  padding: 20px;
  z-index: 998;
  overflow: auto;
}

#sidebarFavs {
  height: 10px;
}

#backToExplore {
    background-color: green;
    position: fixed;
    margin-top: 800px; /* push the button to the bottom of the div */
    margin-left: 20px;
    z-index: 999;
}

#backToExplore:hover {
  background: #0090FF;
  color: black;
}

@media (max-width: 1000px) {
  .favorites-sidebar {
    display: none;
  }
}

</style>


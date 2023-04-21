<template>
    <div class='list-container'>
        <br>
        <br>
        <br>
        <itinerary-list />
        <itinerary-favorites-side-bar />
    </div>
    
</template>

<script>
import itineraryList from "../components/ItineraryList.vue";
import itineraryFavoritesSideBar from '../components/ItineraryFavoritesSideBar.vue'

export default {
    components: { 
        itineraryList,
        itineraryFavoritesSideBar
    },

}
</script>

<style>
    .list-container {
        display: flex;
        flex-direction: column;
        align-items: center;
        background-image: url(../../assets/clevelandOpacityFifty.jpg);
        background-attachment: fixed;
        background-position: center;
        background-repeat: no-repeat;
        background-size: cover;
        min-width: 100vw;
        min-height: 100vh;
    }
</style>
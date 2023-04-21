<template>
    <div class="card-container">
        <div class="destination-card"
            @click="handleClick"
                :class="{flipped : flipped}"
                v-on:click='flip()' >
            <div class='card-front'>
                <img class="thumbnail" :src="destination.imageUrl" />
                <h3 >{{ destination.name }}</h3>
                <div class="button-container">
                    <label for="favorite"> Favorite: </label>
                    <input type="checkbox" id="favorite" name="favorite" value="yes"  
                    v-on:click.stop v-on:click="addToFavorites(destination)" v-model="isChecked">
                </div>
            </div>
            <div class='card-back'>
                <p class="description" >{{ destination.description }}</p>
                <div class="rating" >
                    <img src="../../assets/star.png"
                        class="ratingStar"
                        v-for="n in roundedRating"
                        v-bind:key="n"/>
                </div>
                <div>
                    <button class='tag' id='type' v-if="(destination.type)">{{ destination.type }}</button>
                    <button class='tag' id='outdoor' v-if="(destination.outdoor)">Outdoor</button>
                    <button class='tag' id='kid-friendly' v-if="(destination.kidFriendly)">Kid-Friendly</button>
                    <button class='tag' id='admission' v-if="(destination.freeAdmission)">Admission Fee</button>
                    <button class='tag' id='restaurant-type' v-if="(destination.restaurantType)">{{ destination.restaurantType }}</button>
                </div>
                <h3 v-on:click='flip()' >{{ destination.name }}</h3>
                <div class="button-container">
                    <label for="favorite"> Favorite: </label>
                    <input type="checkbox" id="favorite" name="favorite" value="yes"  
                    v-on:click.stop v-model="isChecked">
                </div> 
            </div>
        </div>
    </div>
</template>

<script>

import destinationsService from '../services/DestinationsService';
// import ItineraryService from '../services/ItineraryService'
import favoriteService from '../services/FavoritesService'

export default {
    props: ['id',],
    data() {
        return {
            flipped: false,
            selected: false,
            destination: {},
            isChecked: false
        }
    },
    computed: {
        roundedRating() {
            return Math.round(this.destination.landmarkRating);
        },

    },
    methods: {
        flip () {
            this.flipped = !this.flipped;
            if (this.flipped) {
                this.$el.querySelector('#DetailsButton').innerHTML = 'Collapse';
            }
            else {
                this.$el.querySelector('#DetailsButton').innerHTML = 'Details';
            }
        },
        getUrl() {
            return this.destination.imageURL;
        },
        handleClick() {
            this.flip;
        },
        addToFavorites() {
            let userId = this.$store.state.user.id
            favoriteService.saveFavoriteToDatabase(this.destination, userId)
        }
    },
    toggleFavorite(destination) {
      this.$store.commit('TOGGLE_FAVORITE', destination);
    },
    created() {
        destinationsService.getDestinationById(this.id).then(response => {
            this.destination = response.data;
        });
    },
    watch: {
    isChecked(newValue) {
      if (newValue) {
        this.$store.commit('ADD_TO_FAVORITES', this.destination);
      } else {
        this.$store.commit('REMOVE_FROM_FAVORITES', this.destination);
      } 
    },
  }
}
</script>

<style>
.card-container {
    perspective: 1000px;
}

.destination-card {
    cursor: pointer;
    transform-style: preserve-3d;
    transition: transform 0.6s;
    display: flex;
    flex-direction: column;
}

.flipped {
    transform: rotateY(180deg);
}

.card-front, .card-back {
    backface-visibility: hidden;
    top: 0;
    left: 0;
    width: 15vw;
    min-width: 300px;
    height: 500px;
    background-color: rgb(230, 230, 230);
    margin: 10px;
    padding: 10px;
    border-radius: 10px;
}

.card-back {
    transform: rotateY(180deg);
    position: absolute;
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
}

.thumbnail {
    max-width: 100%;
    position: relative;
    top: 0px;
    left: 0px;
    aspect-ratio: 1/1.3;
    object-fit: cover;
}

.rating {
    margin-bottom: 15px;
}

.ratingStar {
    max-width: 25px;
}

.button-container {
    display: flex;
    flex-direction: row;
    justify-content: center;
    margin-bottom: 20px;
}

h3 {
    margin-bottom: 2px;
    cursor: pointer;
}


</style>
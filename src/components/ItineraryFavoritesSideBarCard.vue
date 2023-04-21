<template>
    <div class="sidebar-destination-card" v-show='!added'>
        <div id='nameAdd'>
            <h6>{{ destination.name }}</h6>
            <button class='tag' id='add' v-on:click='addToItinerary()'>Add</button>
        </div>
        <img class="sidebarThumbnail" :src="destination.imageUrl" />
    </div>
</template>

<script>

import destinationsService from '../services/DestinationsService';

export default {
    props: ['id'],
    data() {
        return {
            destination: {}
        }
    },
    computed: {
        added() {
            let result = false;
            this.$store.state.currentItineraryDestinations.forEach((d) => {
                if (d.name === this.destination.name) {
                    result = true;
                }
            });
            return result;
        }
    },
    created() {
        destinationsService.getDestinationById(this.id).then(response => {
            this.destination = response.data;
        });
    },
    methods: {
        addToItinerary() {
            this.$store.state.currentItineraryDestinations.push(this.destination);
        }
    }
}
</script>

<style>
.sidebar-destination-card {
    display: flex;
    flex-direction: row;
    justify-content: center;
    flex-wrap: nowrap;
    align-items: flex-start;
    background-color: lightgray;
    margin-bottom: 10px;
    padding: 10px;
    border-radius: 10px;
    width: 25vw;
    min-width: 50px;
    height: 100%;
}

.sidebarThumbnail {
    max-width: 50%;
    position: relative;
    top: 0px;
    left: 0px;
    aspect-ratio: 1/1;
    object-fit: cover;
}

#add {
    background-color: green;
    scale: 80%;
}

#nameAdd {
    display: flex;
    flex-direction: column;
}

h6 {
    margin: 5px;
}

</style>
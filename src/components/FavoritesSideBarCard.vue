<template>
    <div class="sidebar-destination-card">
        <h6>{{ destination.name }}</h6>
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
    created() {
        destinationsService.getDestinationById(this.id).then(response => {
            this.destination = response.data;
        });
    },
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

</style>
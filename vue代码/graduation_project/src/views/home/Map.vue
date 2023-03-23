<template>
  <div id="map"></div>
</template>

<script setup>
import { onMounted } from "vue";
import {initMap} from '../../js/home/map.js'

onMounted(() => {
  const map=document.getElementById('map')
  initMap(map)
});

</script>

<style scoped>
#map {
  width: 500px;
  height: 500px;
  border: 1px solid red;
}
</style>

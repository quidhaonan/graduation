<template>
  <div id="map">
    <div class="map1"></div>
    <div class="map2"></div>
    <div class="map3"></div>
    <div id="chart"></div>
  </div>
</template>

<script setup>
import '../../js/flexible'
import { onMounted } from "vue";
import {initMap} from '../../js/middle/mapOfChina.js'

onMounted(() => {
  const map=document.getElementById('chart')
  initMap(map)
});

</script>

<style lang="less" scoped>
#map {
  position: relative;
  // 810px
  height: 10.125rem;
  #chart {
    position: absolute;
    top: 0;
    left: 0;
    z-index: 5;
    // 810px
    height: 10.125rem;
    width: 100%;
  }
  .map1,
  .map2,
  .map3 {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    // 518px
    width: 6.475rem;
    // 518px
    height: 6.475rem;
    background: url(../../assets/map.png) no-repeat;
    background-size: 100% 100%;
    opacity: 0.3;
  }
  .map2 {
    // 643px
    width: 8.0375rem;
    // 643px
    height: 8.0375rem;
    background-image: url(../../assets/lbx.png);
    opacity: 0.6;
    animation: rotate 15s linear infinite;
    z-index: 2;
  }
  .map3 {
    // 566px
    width: 7.075rem;
    // 566px
    height: 7.075rem;
    background-image: url(../../assets/jt.png);
    animation: rotate1 10s linear infinite;
  }
  @keyframes rotate {
    from {
      transform: translate(-50%, -50%) rotate(0deg);
    }
    to {
      transform: translate(-50%, -50%) rotate(360deg);
    }
  }
  @keyframes rotate1 {
    from {
      transform: translate(-50%, -50%) rotate(0deg);
    }
    to {
      transform: translate(-50%, -50%) rotate(-360deg);
    }
  }

}

</style>

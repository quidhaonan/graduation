<template>
  <!-- <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
  </nav>
  <router-view/> -->

  <Map></Map>
  <FirstLevelCounts></FirstLevelCounts>
  <PurchasingHeat></PurchasingHeat>
  <InquiryTradedAssess></InquiryTradedAssess>
  <!-- <Test></Test> -->
</template>

<script setup>
  import HelloWorld from './components/HelloWorld.vue'
  import Map from './views/home/Map.vue'
  import FirstLevelCounts from './views/home/FirstLevelCounts.vue'
  import PurchasingHeat from './views/home/PurchasingHeat.vue'
  import InquiryTradedAssess from './views/home/InquiryTradedAssess.vue'
  import Test from './views/Test.vue'
  
</script>

<style lang="less">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>

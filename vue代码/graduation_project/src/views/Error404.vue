<template>
  <div class="box">
    <img src="../assets/img/404.jpg" @click="$router.push('/')" />
  </div>
</template>

<script setup>
</script>

<style lang='less' scoped>
.box {
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
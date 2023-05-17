<template>
  <h1 ref="title" id="title"></h1>
</template>

<script setup>
import "../../js/flexible";
import { onMounted, ref } from "vue";
import { initTitle } from "../../js/header/topTitle";

const title = ref(null);
onMounted(() => {
  initTitle(title.value);

  // 浏览器大小改变后字体跟着变（目前没用）
  window.addEventListener("resize", () => {
    const titleDom = document.getElementsByTagName("h1")[0];
    initTitle(titleDom);
  });
});
</script>

<style lang='less' scoped>
h1 {
  width: 24rem;
  height: 1.25rem;
  // 38px
  font-size: 0.475rem;
  color: #fff;
  text-align: center;
  // 80px
  line-height: 1rem;
}
</style>
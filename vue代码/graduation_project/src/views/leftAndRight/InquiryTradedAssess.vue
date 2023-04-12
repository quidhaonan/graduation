<template>
  <div class="panel">
    <h2>询价成交评价</h2>
    <div id="container">
      <div id="left"></div>
      <div id="middle"></div>
      <div id="right"></div>
    </div>
    <div class="panel-footer"></div>
  </div>
</template>

<script setup>
import { onMounted } from "vue";
import { initInquiryTradedAssess } from "../../js/leftAndRight/inquiryTradedAssess";

onMounted(() => {
  const leftDom = document.getElementById("left");
  const middleDom = document.getElementById("middle");
  const rightDom = document.getElementById("right");
  initInquiryTradedAssess(leftDom, middleDom, rightDom);
});
</script>

<style lang="less" scoped>
#container {
  // border: 1px solid red;
  display: flex;
  justify-content: space-between;
  margin-top: -40px;

  width: 100%;
  height: 100%;

  #left {
    width: 30%;
    height: 100%;
    display: 1;
  }
  #middle {
    width: 30%;
    height: 100%;
    display: 1;
  }
  #right {
    width: 30%;
    height: 100%;
    display: 1;
  }
}
</style>
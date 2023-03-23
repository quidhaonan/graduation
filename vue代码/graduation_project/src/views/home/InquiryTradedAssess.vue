<template>
  <div id="container">
    <div id="left"></div>
    <div id="middle"></div>
    <div id="right"></div>
  </div>
</template>

<script setup>
import * as echarts from "echarts";
import { onMounted } from "vue";
import {initInquiryTradedAssess} from '../../js/home/inquiryTradedAssess'

onMounted(() => {
    const leftDom=document.getElementById('left')
    const middleDom=document.getElementById('middle')
    const rightDom=document.getElementById('right')
  initInquiryTradedAssess(leftDom,middleDom,rightDom)
})
</script>

<style scoped>
#container {
  border: 1px solid #bfa;
  display: flex;
  justify-content: space-between;
}
#left {
  width: 400px;
  height: 400px;
}
#middle {
  width: 400px;
  height: 400px;
}
#right {
  width: 400px;
  height: 400px;
}
</style>
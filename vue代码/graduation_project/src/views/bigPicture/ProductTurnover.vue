<template>
  <div class="panel">
    <h2>农产品营业额</h2>
    <div class="chart">
      <div ref="productTurnover" id="productTurnover"></div>
    </div>
    <div class="panel-footer"></div>
  </div>
</template>

<script setup>
import { initProductTurnover } from "../../js/bigPicture/productTurnover";
import { onMounted, ref } from "vue";

const productTurnover = ref(null);

onMounted(() => {
  initProductTurnover(productTurnover.value);
});
</script>

<style lang="less" scoped>
#productTurnover {
  width: 100%;
  height: 100%;
  margin-top: -40px;
}
.panel {
    margin-left: 50px;
    margin-top: 50px;
    padding: 0 !important;
    position: relative;
    // 310px
    // height: 3.875rem;
    // height: 5.8125rem;
    height: 666px;
    width: 1280px;
    // 15px 40px
    padding: 0.1875rem 0.5rem;
    // background-color: pink;
    border: 1px solid rgba(25, 186, 139, 0.17);
    // 15px
    margin-bottom: 0.1875rem;
    background: url("../../assets/img/line.png") rgba(255, 255, 255, 0.03);
    &::before {
      position: absolute;
      top: 0;
      left: 0;
      width: 10px;
      height: 10px;
      border-left: 2px solid #02a6b5;
      border-top: 2px solid #02a6b5;
      content: "";
    }
    &::after {
      position: absolute;
      top: 0;
      right: 0;
      width: 10px;
      height: 10px;
      border-right: 2px solid #02a6b5;
      border-top: 2px solid #02a6b5;
      content: "";
    }
    .panel-footer {
      position: absolute;
      bottom: 0;
      left: 0;
      width: 100%;
      &::before {
        position: absolute;
        left: 0;
        bottom: 0;
        width: 10px;
        height: 10px;
        border-left: 2px solid #02a6b5;
        border-bottom: 2px solid #02a6b5;
        content: "";
      }
      &::after {
        position: absolute;
        bottom: 0;
        right: 0;
        width: 10px;
        height: 10px;
        border-right: 2px solid #02a6b5;
        border-bottom: 2px solid #02a6b5;
        content: "";
      }
    }
    h2 {
      // 48px
      height: 0.6rem;
      // 48px
      line-height: 0.6rem;
      text-align: center;
      // 20px
      font-size: 0.25rem;
      font-weight: 400;
    }
    .chart {
      // 240px（太小了）
      // height: 3rem;
      height: 100%;
      // width: 100%;
    }
  }
</style>
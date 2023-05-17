<template>
  <header>
    <TopTitle />
    <ShowTime />
  </header>
  <section id="mainbox">
    <div class="column">
      <AllLevelCounts />
      <AllLevelTurnover />
    </div>
    <div class="column">
      <TotalCounts />
      <MapOfChina />
    </div>
    <div class="column">
      <MinimumTransactionAmountCorrelation />
      <!-- <PurchasingHeat /> -->
      <TemperatureBand />
    </div>
  </section>
  <footer>
    <div class="column">
      <ProductTurnover />
    </div>
    <div class="column"></div>
    <div class="column">
      <ShopScatterPlot />
    </div>
  </footer>
</template>

<script setup>
import "../js/flexible";
import TopTitle from "../views/header/TopTitle.vue";
import ShowTime from "../views/header/ShowTime.vue";

import AllLevelCounts from "../views/leftAndRight/AllLevelCounts.vue";
import AllLevelTurnover from "../views/leftAndRight/AllLevelTurnover";

import TotalCounts from "./middle/TotalCounts.vue";
import MapOfChina from "../views/middle/MapOfChina.vue";

import MinimumTransactionAmountCorrelation from "./leftAndRight/MinimumTransactionAmountCorrelation.vue";
// import PurchasingHeat from "../views/leftAndRight/PurchasingHeat.vue";
import TemperatureBand from "../views/leftAndRight/TemperatureBand";

import ProductTurnover from "../views/bigPicture/ProductTurnover.vue";
import ShopScatterPlot from "../views/bigPicture/ShopScatterPlot.vue";
</script>

<style lang="less">
// 声明字体
@font-face {
  font-family: electronicFont;
  src: url(../font/DS-DIGIT.TTF);
}
li {
  list-style: none;
}
header {
  position: relative;
  // 100px
  height: 1.25rem;
  background: url("../assets/img/head_bg.png") no-repeat;
  background-size: 100% 100%;
}
#mainbox {
  display: flex;
  min-width: 1024px;
  max-width: 1920px;
  margin: 0 auto;
  // 10px
  padding: 0.125rem 0.125rem 0;
  .column {
    flex: 3;
  }
  .column:nth-child(2) {
    // border: 1px solid red;
    flex: 5;
    // 10px 15px
    // margin: 0 0.125rem 0.1875rem;
    margin: 0 0.125rem 0;
  }
  .panel {
    padding: 0 !important;
    position: relative;
    // 310px
    // height: 3.875rem;
    height: 5.8125rem;
    // 15px 40px
    padding: 0.1875rem 0.5rem;
    // background-color: pink;
    border: 1px solid rgba(25, 186, 139, 0.17);
    // 15px
    margin-bottom: 0.1875rem;
    background: url("../assets/img/line.png") rgba(255, 255, 255, 0.03);
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
}
footer {
  display: flex;
  min-width: 1024px;
  max-width: 1920px;
  margin: 0 auto;
  // 10px
  padding: 0.125rem 0.125rem 0;
  .column {
    flex: 99;
    // border: 1px solid red;
    height: 500px;
  }
  .column:nth-child(2) {
    // border: 1px solid red;
    flex: 1;
    // 10px 15px
    // margin: 0 0.125rem 0.1875rem;
  }
  .panel {
    padding: 0 !important;
    position: relative;
    // 310px
    // height: 3.875rem;
    // height: 5.8125rem;
    height: 10rem;
    // 15px 40px
    padding: 0.1875rem 0.5rem;
    // background-color: pink;
    border: 1px solid rgba(25, 186, 139, 0.17);
    // 15px
    margin-bottom: 0.1875rem;
    background: url("../assets/img/line.png") rgba(255, 255, 255, 0.03);
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
}
</style>
<template>
  <div class="panel">
    <h2>商铺分析</h2>
    <div class="chart">
      <div id="container">
      <div id="shopScatterPlot"></div>
      <div class="dg main" id="dropDownBox">
        <ul v-show="isShow">
          <li class="cr string">
            <div>
              <span class="property-name">xAxis3D</span>
              <div class="c">
                <select id="selectX">
                  <option value="店铺地区">店铺地区</option>
                  <option value="是否是VIP">是否是VIP</option>
                  <option value="店铺热度">店铺热度</option>
                  <option value="店铺等级">店铺等级</option>
                  <option value="店铺总营业额" selected>店铺总营业额</option>
                  <option value="店铺粉丝数">店铺粉丝数</option>
                  <option value="平均发货速度">平均发货速度</option>
                  <option value="售后率">售后率</option>
                  <option value="复购率">复购率</option>
                  <option value="店铺评分">店铺评分</option>
                </select>
              </div>
            </div>
          </li>
          <li class="cr string">
            <div>
              <span class="property-name">yAxis3D</span>
              <div class="c">
                <select id="selectY">
                  <option value="店铺地区" selected>店铺地区</option>
                  <option value="是否是VIP">是否是VIP</option>
                  <option value="店铺热度">店铺热度</option>
                  <option value="店铺等级">店铺等级</option>
                  <option value="店铺总营业额">店铺总营业额</option>
                  <option value="店铺粉丝数">店铺粉丝数</option>
                  <option value="平均发货速度">平均发货速度</option>
                  <option value="售后率">售后率</option>
                  <option value="复购率">复购率</option>
                  <option value="店铺评分">店铺评分</option>
                </select>
              </div>
            </div>
          </li>
          <li class="cr string">
            <div>
              <span class="property-name">zAxis3D</span>
              <div class="c">
                <select id="selectZ">
                  <option value="店铺地区">店铺地区</option>
                  <option value="是否是VIP" selected>是否是VIP</option>
                  <option value="店铺热度">店铺热度</option>
                  <option value="店铺等级">店铺等级</option>
                  <option value="店铺总营业额">店铺总营业额</option>
                  <option value="店铺粉丝数">店铺粉丝数</option>
                  <option value="平均发货速度">平均发货速度</option>
                  <option value="售后率">售后率</option>
                  <option value="复购率">复购率</option>
                  <option value="店铺评分">店铺评分</option>
                </select>
              </div>
            </div>
          </li>
          <li class="cr string">
            <div>
              <span class="property-name">color</span>
              <div class="c">
                <select id="selectC">
                  <option value="店铺地区" selected>店铺地区</option>
                  <option value="是否是VIP">是否是VIP</option>
                  <option value="店铺热度">店铺热度</option>
                  <option value="店铺等级">店铺等级</option>
                  <option value="店铺总营业额">店铺总营业额</option>
                  <option value="店铺粉丝数">店铺粉丝数</option>
                  <option value="平均发货速度">平均发货速度</option>
                  <option value="售后率">售后率</option>
                  <option value="复购率">复购率</option>
                  <option value="店铺评分">店铺评分</option>
                </select>
              </div>
            </div>
          </li>
          <li class="cr string">
            <div>
              <span class="property-name">symbolSize</span>
              <div class="c">
                <select id="selectS">
                  <option value="店铺地区">店铺地区</option>
                  <option value="是否是VIP">是否是VIP</option>
                  <option value="店铺热度">店铺热度</option>
                  <option value="店铺等级">店铺等级</option>
                  <option value="店铺总营业额">店铺总营业额</option>
                  <option value="店铺粉丝数">店铺粉丝数</option>
                  <option value="平均发货速度">平均发货速度</option>
                  <option value="售后率">售后率</option>
                  <option value="复购率">复购率</option>
                  <option value="店铺评分" selected>店铺评分</option>
                </select>
              </div>
            </div>
          </li>
        </ul>
        <div class="close-button close-bottom" @click="tes" id="close">
          Close Controls
        </div>
      </div>
    </div>
    </div>
    <div class="panel-footer"></div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import {
  initShopScatterPlot,
  initSelect,
} from "../../js/bigPicture/ShopScatterPlot";

onMounted(() => {
  const shopScatterPlotDom = document.getElementById("shopScatterPlot");
  const selectXDom = document.getElementById("selectX");
  const selectYDom = document.getElementById("selectY");
  const selectZDom = document.getElementById("selectZ");
  const selectCDom = document.getElementById("selectC");
  const selectSDom = document.getElementById("selectS");

  initShopScatterPlot(shopScatterPlotDom);
  initSelect(selectXDom, selectYDom, selectZDom, selectCDom, selectSDom);
});

// 给下拉框添加控制消失与显示的事件
let isShow = ref(true);
function tes() {
  if (isShow.value) {
    document.getElementById("close").innerHTML = "Open Controls";
    isShow.value = false;
  } else {
    document.getElementById("close").innerHTML = "Close Controls";
    isShow.value = true;
  }
}
</script>

<style scoped>
#container {
  position: relative;
  width: 100%;
  height: 100%;
  /* margin-top: -40px; */
  /* border: 1px solid red; */
}
#shopScatterPlot {
  position: relative;
  /* width: 800px;
  height: 700px; */
  width: 100%;
  height: 100%;
  margin-top: -40px;
  /* border: 1px solid red; */
}
/* 此为原本有的行内样式换为页内样式 */
#dropDownBox {
  width: 245px;
  position: absolute;
  right: 0px;
  top: 0px;
  z-index: 1000;
}
#close {
  width: 245px;
  margin-bottom: -20px;
}

/* 下拉框的样式 */
* {
  margin: 0;
  padding: 0;
}
div {
  display: block;
}
.dg {
  color: #eee;
  font: 11px Lucida Grande, sans-serif;
  text-shadow: 0 -1px 0 #111;
}
.dg .c {
  float: left;
  width: 60%;
  position: relative;
}
.dg .c select {
  margin-top: 5px;
}
.dg .cr {
  clear: both;
  overflow: hidden;
}
.dg .cr.string {
  border-left: 3px solid #1ed36f;
}
.dg li {
  transition: overflow 0.1s linear;
}
.dg li:not(.folder) {
  /* background: #1a1a1a; */
  background: #9a60b4;
  /* border-bottom: 1px solid #2c2c2c; */
  border-bottom: 1px solid #9a60b4;
}
.dg li:not(.folder) {
  cursor: auto;
  height: 27px;
  line-height: 27px;
  padding: 0 4px 0 5px;
}
.dg .property-name {
  cursor: default;
  float: left;
  clear: left;
  width: 40%;
  overflow: hidden;
  text-overflow: ellipsis;
}
.dg ul {
  list-style: none;
  margin: 0;
  padding: 0;
  width: 100%;
  clear: both;
}
.dg.main {
  transition: opacity 0.1s linear;
}
.dg.main .c select {
  color: #000;
  /* color: #9a60b4; */
}
.dg.main .close-button {
  transition: opacity 0.1s linear;
  border: 0;
  line-height: 19px;
  height: 20px;
  cursor: pointer;
  text-align: center;
  /* background-color: #000; */
  background-color: #9a60b4;
}
.dg.main .close-button.close-bottom {
  position: absolute;
}
.dg.main * {
  box-sizing: content-box;
}
.dg.main::-webkit-scrollbar {
  width: 5px;
  background: #1a1a1a;
}
.dg.main::-webkit-scrollbar-corner {
  height: 0;
  display: none;
}
.dg.main::-webkit-scrollbar-thumb {
  border-radius: 5px;
  background: #676767;
}

element.style {
  width: 245px;
  position: absolute;
  right: 0px;
  top: 0px;
  z-index: 1000;
}
li {
  display: list-item;
  text-align: -webkit-match-parent;
}
option {
  font-weight: normal;
  display: block;
  white-space: nowrap;
  min-height: 1.2em;
}
select {
  -webkit-writing-mode: horizontal-tb !important;
  font-style: ;
  font-variant-ligatures: ;
  font-variant-caps: ;
  font-variant-numeric: ;
  font-variant-east-asian: ;
  font-weight: ;
  font-stretch: ;
  font-size: ;
  font-family: ;
  text-rendering: auto;
  letter-spacing: normal;
  word-spacing: normal;
  line-height: normal;
  text-transform: none;
  text-indent: 0px;
  text-shadow: none;
  display: inline-block;
  text-align: start;
  appearance: auto;
  align-items: center;
  white-space: pre;
  -webkit-rtl-ordering: logical;
  background-color: -internal-light-dark(rgb(255, 255, 255), rgb(59, 59, 59));
  cursor: default;
  border-width: 1px;
  border-style: solid;
  border-color: -internal-light-dark(rgb(118, 118, 118), rgb(133, 133, 133));
  border-image: initial;
  border-radius: 0px;
}
select:not(:-internal-list-box) {
  overflow: visible !important;
}

ul {
  display: block;
  list-style-type: disc;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  padding-inline-start: 40px;
}
</style>
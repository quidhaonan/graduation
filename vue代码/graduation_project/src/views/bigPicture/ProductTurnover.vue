<template>
  <div class="panel">
    <h2>商品交易总额</h2>
    <div class="chart">
      <div id="productTurnover"></div>
    </div>
    <div class="panel-footer"></div>
  </div>
</template>

<script setup>
import {initProductTurnover} from '../../js/bigPicture/productTurnover'
import { onMounted } from "vue"

    
onMounted(()=>{
const productTurnoverDom=document.getElementById('productTurnover')
initProductTurnover(productTurnoverDom)
})
</script>

<style scoped>
#productTurnover{
    width: 100%;
    height: 100%;

    /* border: 1px solid red; */
    margin-top: -40px;
}
</style>
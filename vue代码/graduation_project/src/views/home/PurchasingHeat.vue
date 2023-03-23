<template>
  <div id="purchasingHeat"></div>
</template>

<script setup>
import { onMounted } from "vue"
import {initPurchasingHeat,initData} from '../../js/home/purchasingHeat'



onMounted(async () => {
  // 也要异步，刚开始用到 挂载前 的生命周期钩子中，导致数据为空
  await initData()
  const purchasingHeat=document.getElementById('purchasingHeat')
  initPurchasingHeat(purchasingHeat)
})
</script>

<style scoped>
#purchasingHeat{
    border: 1px solid red;
    height: 600px;
    width: 600px;
}
</style>
<template>
  <div class="panel bar">
    <h2>商品采购热度</h2>
    <div class="chart">
      <div id="purchasingHeat"></div>
    </div>
    <div class="panel-footer"></div>
  </div>
</template>

<script setup>
import { onMounted } from "vue";
import { initPurchasingHeat } from "../../js/leftAndRight/purchasingHeat";

onMounted(() => {
  // 也要异步，刚开始用到 挂载前 的生命周期钩子中，
  // 导致数据为空（ await initData()这个操作移到 initPurchasingHeat）
  const purchasingHeat = document.getElementById("purchasingHeat");
  initPurchasingHeat(purchasingHeat);
});
</script>

<style scoped>
#purchasingHeat {
  height: 100%;
  width: 100%;
  margin-top: -40px;
}
</style>
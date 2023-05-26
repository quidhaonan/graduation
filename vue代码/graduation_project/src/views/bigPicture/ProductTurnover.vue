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
</style>
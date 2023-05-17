<template>
  <div class="panel bar">
    <h2>最低交易金额相关性</h2>
    <div class="chart">
      <div ref="startBatching" id="startBatching"></div>
    </div>
    <div class="panel-footer"></div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { initStartBatching } from "../../js/leftAndRight/minimumTransactionAmountCorrelation";

const startBatching = ref(null);

onMounted(() => {
  initStartBatching(startBatching.value);
});
</script>

<style scoped>
#startBatching {
  height: 100%;
  width: 100%;
}
</style>
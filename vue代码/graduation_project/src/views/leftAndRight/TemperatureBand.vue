<template>
  <div class="panel bar">
    <h2>温度带相关性</h2>
    <div class="chart">
      <div ref="temperatureBand" id="temperatureBand"></div>
    </div>
    <div class="panel-footer"></div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { initTemperatureBand } from "../../js/leftAndRight/temperatureBand";

const temperatureBand = ref(null);

onMounted(() => {
  initTemperatureBand(temperatureBand.value);
});
</script>

<style lang="less" scoped>
#temperatureBand {
  height: 100%;
  width: 100%;
}
</style>
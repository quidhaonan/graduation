<template>
  <div class="panel bar">
    <h2>起批量的关系</h2>
    <div class="chart">
      <div id="startBatching"></div>
    </div>
    <div class="panel-footer"></div>
  </div>
</template>

<script setup>
import { onMounted } from "vue"
import {initStartBatching} from '../../js/leftAndRight/startBatchingRelationship'

onMounted(() => {
  const chartDom = document.getElementById("startBatching")
  initStartBatching(chartDom)
});
</script>

<style scoped>
#startBatching{
    height: 100%;
    width: 100%;
}
</style>
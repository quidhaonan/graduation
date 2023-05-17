<template>
  <div class="panel bar">
    <h2>农产品上架数量分布</h2>
    <div class="chart">
      <div ref="secondLevelCounts" id="secondLevelCounts">
        <!-- popper-class='popover' 是为了 js 中获得它（目前没用上） -->
        <el-popover
          placement="right"
          popper-class="popover"
          offset="200"
          :width="500"
          trigger="click"
          show-after="100"
          @after-enter="initRoseDiagram"
          @after-leave="leaves"
          @hide="hides"
          @show="shows"
        >
          <template #reference>
            <div class="popover-trigger">
              <el-button ref="autoClick" id="autoClick"></el-button>
            </div>
          </template>
          <div id="showRoseDiagram" class="clearfix"></div>
        </el-popover>
      </div>
    </div>
    <div class="panel-footer"></div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import {
  initFirstLevel,
  initRoseDiagram,
} from "../../js/leftAndRight/allLevelCounts";

const secondLevelCounts = ref(null);
const autoClick = ref(null);

onMounted(() => {
  initFirstLevel(secondLevelCounts.value, document.getElementById('autoClick'));
  // initFirstLevel(secondLevelCounts.value, autoClick.value);
});
</script>

<style lang="less" scoped>
#secondLevelCounts {
  position: relative;
  height: 100%;
  width: 100%;
}
.popover {
  position: absolute;
}
#showRoseDiagram {
  height: 400px;
  width: 500px;
}
.clearfix::before,
.clearfix::after {
  content: "";
  display: table;
  clear: both;
}
.popover-trigger {
  position: absolute;
  top: 50px;
  left: 50px;
}
</style>
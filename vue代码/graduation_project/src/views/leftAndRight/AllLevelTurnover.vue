<template>
  <div class="panel bar">
    <h2>农产品总营业额分布</h2>
    <div class="chart">
      <div ref="secondLevelTurnover" id="secondLevelTurnover">
        <!-- popper-class='popover' 是为了 js 中获得它（目前没用上） -->
        <el-popover
          placement="right"
          popper-class="popover"
          offset="200"
          :width="500"
          trigger="click"
          show-after="100"
          @after-enter="initRoseDiagramTurnover"
          @after-leave="leaves"
          @hide="hides"
          @show="shows"
        >
          <template #reference>
            <div class="popover-trigger">
              <el-button
                ref="autoClickTurnover"
                id="autoClickTurnover"
              ></el-button>
            </div>
          </template>
          <div id="showRoseDiagramTurnover" class="clearfix"></div>
        </el-popover>
      </div>
    </div>

    <div class="panel-footer"></div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import {
  initAllLevelTurnover,
  initRoseDiagramTurnover,
} from "../../js/leftAndRight/allLevelTurnover";

const secondLevelTurnover = ref(null);
const autoClickTurnover = ref(null);

onMounted(() => {
  initAllLevelTurnover(secondLevelTurnover.value, document.getElementById('autoClickTurnover'));
});
</script>

<style lang="less" scoped>
#secondLevelTurnover {
  position: relative;
  height: 100%;
  width: 100%;
  margin-top: -40px;
}
.popover {
  position: absolute;
}
#showRoseDiagramTurnover {
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
<template>
  <div class="panel bar">
    <h2>商品销售额分布</h2>
    <div class="chart">
      <div id="secondLevelTurnover">
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
              <el-button id="autoClickTurnover"></el-button>
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
import { onMounted } from "vue";
import {
  initAllLevelTurnover,
  initRoseDiagramTurnover,
} from "../../js/leftAndRight/allLevelTurnover";

onMounted(() => {
  const secondLevelCounts = document.getElementById("secondLevelTurnover");
  const autoClick = document.getElementById("autoClickTurnover");
  initAllLevelTurnover(secondLevelCounts, autoClick);
});
</script>

<style lang="less" scoped>
#secondLevelTurnover {
  position: relative;
  height: 100%;
  width: 100%;
// height: 500px;
// width: 500px;
  // 待会删除
//   border: 1px solid red;
  margin-top: -40px;
}
.popover {
  position: absolute;
}
#showRoseDiagramTurnover {
  /* position: absolute;
  left: 100px;
  border: 1px solid red; */
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
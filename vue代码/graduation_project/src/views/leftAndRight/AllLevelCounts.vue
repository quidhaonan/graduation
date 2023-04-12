<template>
  <div class="panel bar">
    <h2>商品上架数量分布</h2>
    <div class="chart">
      <div id="secondLevelCounts">
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
              <el-button id="autoClick"></el-button>
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
import { onMounted } from "vue";
import {
  initFirstLevel,
  initRoseDiagram,
} from "../../js/leftAndRight/allLevelCounts";

onMounted(() => {
  const secondLevelCounts = document.getElementById("secondLevelCounts");
  const autoClick = document.getElementById("autoClick");
  initFirstLevel(secondLevelCounts, autoClick);
});
</script>

<style lang="less" scoped>
#secondLevelCounts {
  // left: 50px;
  position: relative;
  height: 100%;
  width: 100%;
  // height: 500px;
  // width: 500px;
  // 待会删除
  // border: 1px solid red;
}
.popover {
  position: absolute;
}
#showRoseDiagram {
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
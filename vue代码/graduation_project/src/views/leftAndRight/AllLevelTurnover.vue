<template>
  <div class="panel bar">
    <!-- <h2>农产品总营业额分布</h2> -->
    <h2>农产品总营业额分析</h2>
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
.panel {
    margin-left: 50px;
    margin-top: 50px;
    padding: 0 !important;
    position: relative;
    // 310px
    // height: 3.875rem;
    // height: 5.8125rem;
    height: 666px;
    width: 666px;
    // 15px 40px
    padding: 0.1875rem 0.5rem;
    // background-color: pink;
    border: 1px solid rgba(25, 186, 139, 0.17);
    // 15px
    margin-bottom: 0.1875rem;
    background: url("../../assets/img/line.png") rgba(255, 255, 255, 0.03);
    &::before {
      position: absolute;
      top: 0;
      left: 0;
      width: 10px;
      height: 10px;
      border-left: 2px solid #02a6b5;
      border-top: 2px solid #02a6b5;
      content: "";
    }
    &::after {
      position: absolute;
      top: 0;
      right: 0;
      width: 10px;
      height: 10px;
      border-right: 2px solid #02a6b5;
      border-top: 2px solid #02a6b5;
      content: "";
    }
    .panel-footer {
      position: absolute;
      bottom: 0;
      left: 0;
      width: 100%;
      &::before {
        position: absolute;
        left: 0;
        bottom: 0;
        width: 10px;
        height: 10px;
        border-left: 2px solid #02a6b5;
        border-bottom: 2px solid #02a6b5;
        content: "";
      }
      &::after {
        position: absolute;
        bottom: 0;
        right: 0;
        width: 10px;
        height: 10px;
        border-right: 2px solid #02a6b5;
        border-bottom: 2px solid #02a6b5;
        content: "";
      }
    }
    h2 {
      // 48px
      height: 0.6rem;
      // 48px
      line-height: 0.6rem;
      text-align: center;
      // 20px
      font-size: 0.25rem;
      font-weight: 400;
    }
    .chart {
      // 240px（太小了）
      // height: 3rem;
      height: 100%;
      // width: 100%;
    }
  }
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
<template>
  <div id="container">
    <!-- 搜索框 -->
    <!-- <el-input v-model="input" placeholder="Please input" />
    <el-button type="primary" plain>搜索</el-button> -->
    <div class="search">
        <span>商品名称：</span>
        <el-input
         v-model="input"
          placeholder="Please input"
          input-style="width:175px;"
           />
        <span class="shipFromAddress">发货地址：</span>
        <el-select v-model="roomStateId" class="m-2">
            <el-option
            v-for="item in roomStateList"
            :key="item.roomStateId"
            :label="item.roomStateName"
            :value="item.roomStateId"
            />
        </el-select>
        <el-button
            type="success"
            @click="loadTable"
        >查询</el-button>
        <el-button
            type="info"
            @click="toExcel"
            class="outPut"
        >导出Excel</el-button>
    </div>

    switch parent border: <el-switch v-model="parentBorder" /> switch child
    border: <el-switch v-model="childBorder" />
    <el-table :data="tableData" :border="parentBorder" style="width: 100%">
      <el-table-column type="expand">
        <template #default="props">
          <div m="4">
            <h3>详细</h3>
            <el-table :data="[props.row]"  :border="childBorder">
              <el-table-column label="所属类别" prop="levelName" />
              <el-table-column label="采购热度" prop="purchasingHeat" />
              <el-table-column label="询价人数" prop="inquiry" />
              <el-table-column label="成交人数" prop="traded" />
              <el-table-column label="评价人数" prop="assess" />
              <el-table-column label="起批量" prop="startBatching" />
              <el-table-column label="最后更新时间" prop="updateTime" />
            </el-table>
            <p m="t-0 b-2" v-if="props.row.proDesc"><h3>商品介绍:</h3> {{ props.row.proDesc }}</p>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="商品名称" prop="proName" />
      <el-table-column label="商品价格" prop="price" />
      <el-table-column label="发货地址" prop="shipFromAddress" 
        :filters="[
        { text: '2016-05-01', value: '2016-05-01' },
        { text: '2016-05-02', value: '2016-05-02' },
        { text: '2016-05-03', value: '2016-05-03' },
        { text: '2016-05-04', value: '2016-05-04' },
      ]"
      :filter-method="filterHandler"
      />
      <el-table-column label="收藏人数" prop="collectorsCounts" />
      <el-table-column label="总营业额" prop="turnover" />
      <el-table-column label="详情">
        <template #default="props">
            <el-button
                type="primary"
            >商品详情</el-button>
            <el-button
                type="primary"
            >商铺详情</el-button>
            </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup>
import { onBeforeMount, onMounted, ref } from "vue";
import { $post } from "../utils/request";

// 搜索框
const input = ref("");

let tableData = ref([]);
let parentBorder = ref(false);
let childBorder = ref(false);

function filterHandler(){
  console.log('fefae')
}

onMounted(async () => {
  const result = await $post("/productTable");
  tableData.value = result;
});

console.log(tableData.value);
</script>

<style lang="less" scoped>
#container {
  width: 90%;
  margin: 0 auto;

  // 搜索区域样式
  .search {
    position: relative;
    padding-bottom: 6px;
    font-size: 14px;
    color: #666;
    display: flex;
    align-items: center;
    * {
      margin-right: 5px;
    }
    .shipFromAddress {
      margin-left: 25px;
    }
    .outPut {
      position: absolute;
      right: 0;
      margin-top: 30px;
    }
  }
}
h3 {
  display: inline-block;
}
</style>
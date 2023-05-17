<template>
  <div id="container">
    <!-- 搜索框 -->
    <!-- <el-input v-model="input" placeholder="Please input" />
    <el-button type="primary" plain>搜索</el-button> -->
    <div class="search">
      <span>农产品名称：</span>
      <el-input
        v-model="input"
        placeholder="Please input"
        input-style="width:175px;"
        @keyup.enter="searchByProName(input)"
      />
      <el-button
        class="searchBtn"
        type="success"
        @click.enter="searchByProName(input)"
        >查询</el-button
      >
      <el-button type="info" @click="toExcel(tableData)" class="outPut"
        >导出Excel</el-button
      >
    </div>

    switch parent border: <el-switch v-model="parentBorder" /> switch child
    border: <el-switch v-model="childBorder" />
    <el-table
      :data="tableData"
      :border="parentBorder"
      style="width: 100%"
      @sort-change="sortChange"
    >
      <el-table-column type="expand">
        <template #default="props">
          <div m="4">
            <el-table :data="[props.row]" :border="childBorder">
              <el-table-column label="所属类别" prop="levelName" />
              <el-table-column label="采购热度" prop="purchasingHeat" />
              <el-table-column label="询价人数" prop="inquiry" />
              <el-table-column label="成交人数" prop="traded" />
              <el-table-column label="评价人数" prop="assess" />
              <el-table-column label="起批量" prop="startBatching" />
              <el-table-column label="最后更新时间" prop="updateTime" />
              <el-table-column label="介绍">
                <el-tooltip
                  class="box-item"
                  effect="light"
                  :content="props.row.proDesc"
                  placement="top-end"
                >
                  <el-button type="success" round>介绍</el-button>
                </el-tooltip>
              </el-table-column>
            </el-table>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="名称" prop="proName" />
      <el-table-column label="价格" prop="price" sortable="custom" />
      <el-table-column
        label="发货地址"
        prop="address"
        :filters="[
          { text: '北京市', value: '1' },
          { text: '天津市', value: '2' },
          { text: '河北省', value: '3' },
          { text: '山西省', value: '4' },
          { text: '内蒙古自治区', value: '5' },
          { text: '辽宁省', value: '6' },
          { text: '吉林省', value: '7' },
          { text: '黑龙江省', value: '8' },
          { text: '上海市', value: '9' },
          { text: '江苏省', value: '10' },
          { text: '浙江省', value: '11' },
          { text: '安徽省', value: '12' },
          { text: '福建省', value: '13' },
          { text: '江西省', value: '14' },
          { text: '山东省', value: '15' },
          { text: '河南省', value: '16' },
          { text: '湖北省', value: '17' },
          { text: '湖南省', value: '18' },
          { text: '广东省', value: '19' },
          { text: '广西壮族自治区', value: '20' },
          { text: '海南省', value: '21' },
          { text: '重庆市', value: '22' },
          { text: '四川省', value: '23' },
          { text: '贵州省', value: '24' },
          { text: '云南省', value: '25' },
          { text: '西藏自治区', value: '26' },
          { text: '陕西省', value: '27' },
          { text: '甘肃省', value: '28' },
          { text: '青海省', value: '29' },
          { text: '宁夏回族自治区', value: '30' },
          { text: '新疆维吾尔自治区', value: '31' },
          { text: '香港特别行政区', value: '32' },
          { text: '澳门特别行政区', value: '33' },
          { text: '台湾省', value: '34' },
        ]"
        :filter-method="filterHandler"
        :filtered-value="filteredValues"
      />
      <el-table-column
        label="收藏人数"
        prop="collectorsCounts"
        sortable="custom"
      />
      <el-table-column label="总营业额" prop="turnover" sortable="custom" />
      <el-table-column label="详情">
        <template #default="props">
          <a :href="props.row.proUrl" style="margin-right: 8px" target="_blank">
            <el-button type="primary">商品详情</el-button>
          </a>
          <a :href="props.row.shopUrl" target="_blank">
            <el-button type="primary">商铺详情</el-button>
          </a>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <div class="demo-pagination-block">
      <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="[6, 10, 20, 30]"
        background
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        hide-on-single-page="true"
        @size-change="paginationChange(currentPage, pageSize)"
        @current-change="paginationChange(currentPage, pageSize)"
      />
    </div>
  </div>
</template>

<script setup>
import { onBeforeMount, ref, toRaw, watch } from "vue";
import { $post } from "../../utils/request";
import {
  sortChange,
  filterHandler,
  toExcel,
  searchByProName,
  paginationChange,
  resetShipFromAddress,
} from "../../js/table/productTable";
// 导入全局状态管理
import { useStore } from "vuex";

// 用于地图组件跳转
let filteredValues = ref([]);
const store = useStore();
// 搜索框
const input = ref("");
// 表格数据绑定
let tableData = ref([]);
let parentBorder = ref(false);
let childBorder = ref(false);
// 分页数据绑定和方法
const currentPage = ref(1);
const pageSize = ref(6);
const total = ref("");

onBeforeMount(async () => {
  // 处理从地图组件跳转过来的请求
  if (
    store.getters["productTable/getQueryCriteria"].shipFromAddress["0"] !=
    undefined
  ) {
    filteredValues.value.push(
      store.getters["productTable/getQueryCriteria"].shipFromAddress["0"] + ""
    );
  }

  // 加载表格数据
  const result = await $post(
    "/table/productTableInformation",
    toRaw(store.getters["productTable/getQueryCriteria"])
  );
  tableData.value = result;
  // 用于存储对象的引用，以供更新数据的函数使用将新数据赋值给 tableData
  store.dispatch("productTable/updateTableData", tableData);

  // 加载分页的数据
  total.value = result[0].totalCounts;
  // 用于存储对象的引用，以供更新数据的函数使用将新数据赋值给 total
  store.dispatch("productTable/updateTotal", total);

  const btn = Array.from(document.getElementsByTagName("button"));
  // 添加事件（此时获取 16 个按钮，如果给 window 添加点击事件赋值，会获得 18 个按钮，
  //    新增分页中的前后按钮）
  btn[15].addEventListener("click", resetShipFromAddress);
});

// 实时更新输入框和 vuex 中的数据
watch(input, (newValue) => {
  // 更新 vux 中的数据
  store.dispatch("productTable/updateQueryCriteria", {
    input: newValue,
  });
});
</script>

<style lang="less" scoped>
#container {
  position: relative;
  width: 90%;
  margin: 20px auto;

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
    .searchBtn {
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
a {
  // 去除下划线
  text-decoration: none;
  // 继承父元素的字体颜色
  color: inherit;
}

// 分页的样式
.demo-pagination-block {
  position: absolute;
  right: 0;
  margin-top: 10px;
}
</style>
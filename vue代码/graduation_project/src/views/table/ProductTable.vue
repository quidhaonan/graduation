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
        <el-button
            class="searchBtn"
            type="success"
            @click="searchProName"
        >查询</el-button>
        <el-button
            type="info"
            @click="toExcel"
            class="outPut"
        >导出Excel</el-button>
    </div>

    switch parent border: <el-switch v-model="parentBorder" /> switch child
    border: <el-switch v-model="childBorder" />
    <el-table :data="tableData" 
    :border="parentBorder" 
    style="width: 100%" 
    @sort-change="priceSort"
    >
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
      <el-table-column label="商品价格" prop="price" sortable='custom'/>
      <el-table-column 
        label="发货地址" 
        prop="shipFromAddress" 
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
        { text: '台湾省', value: '34' }
      ]"
      :filter-method="filterHandler"
        />
      <el-table-column label="收藏人数" prop="collectorsCounts" sortable='custom'/>
      <el-table-column label="总营业额" prop="turnover" sortable='custom'/>
      <el-table-column label="详情">
        <template #default="props">
          <a :href="props.row.proUrl" style="margin-right:8px" target="_blank">
            <el-button
                type="primary"
            >商品详情</el-button>
          </a>
          <a :href="props.row.shopUrl" target="_blank">
            <el-button
                type="primary"
            >商铺详情</el-button>
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
      :total="400"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
  </div>
</template>

<script setup>
import { onBeforeMount, onMounted, ref } from "vue";
import { $post } from "../../utils/request";
import {priceSort} from '../../js/table/productTable'
import { xlsx } from "../../utils/xlsx.js";

// 搜索框
const input = ref("");

// 表格数据绑定
let tableData = ref([]);
let parentBorder = ref(false);
let childBorder = ref(false);

onMounted(async () => {
  // 加载表格数据
  const result = await $post("/productTable");
  tableData.value = result;
});
// excel 导出
function toExcel() {
  // 组织导出数据的格式
  const excelData = tableData.value.map((item) => {
    return {
      proName: item.proName,
      price: item.price,
      shipFromAddress: item.shipFromAddress,
      collectorsCounts: item.collectorsCounts,
      turnover: item.turnover,
      proUrl: item.proUrl,
      shopUrl: item.shopUrl,
      levelName: item.levelName,
      purchasingHeat: item.purchasingHeat,
      inquiry: item.inquiry,
      traded: item.traded,
      assess: item.assess,
      startBatching: item.startBatching,
      updateTime: item.updateTime,
      proDesc: item.proDesc,
    };
  });
  // 表头信息
  const excelHeader = {
    proName: "商品名称",
    price: "发货地址",
    shipFromAddress: "发货地址",
    collectorsCounts: "收藏人数",
    turnover: "总营业额",
    proUrl: "商品详情",
    shopUrl: "商铺详情",
    levelName: "所属类别",
    purchasingHeat: "采购热度",
    inquiry: "询价人数",
    traded: "成交人数",
    assess: "评价人数",
    startBatching: "起批量",
    updateTime: "最后更新时间",
    proDesc: "商品介绍",
  };
  // 将数据导出成 excel 文件
  xlsx(excelData, excelHeader, "商品信息");
}

// 分页数据绑定和方法
const currentPage = ref(1)
const pageSize = ref(6)
const handleSizeChange = (val) => {
  // console.log(`${val} items per page`)
  console.log('fef')
}
const handleCurrentChange = (val) => {
  console.log(`current page: ${val}`)
}

const searchProName=()=>{
  console.log('fefe')
}
</script>

<style lang="less" scoped>
#container {
  position: relative;
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
  text-decoration: none; /* 去除下划线 */
  color: inherit; /* 继承父元素的字体颜色 */
}

// 分页的样式
.demo-pagination-block {
  position: absolute;
  right: 0;
  margin-top: 10px;
}
</style>
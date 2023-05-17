<template>
  <div id="container">
<!-- 搜索框 -->
    <!-- <el-input v-model="input" placeholder="Please input" />
    <el-button type="primary" plain>搜索</el-button> -->
    <div class="search">
        <span>商铺名称：</span>
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
        >查询</el-button>
        <el-button
            type="info"
            @click="toExcel(tableData)"
            class="outPut"
        >导出Excel</el-button>
    </div>

    switch parent border: <el-switch v-model="parentBorder" /> switch child
    border: <el-switch v-model="childBorder" />
    <el-table 
      :data="tableData" 
      :border="parentBorder" 
      style="width: 100%"
      @sort-change="sortChange">
      <el-table-column type="expand">
        <template #default="props">
          <div m="4">
            <p m="t-0 b-2" v-if="props.row.shopHeat"><h3>热度:</h3> {{ props.row.shopHeat }}</p>
            <p m="t-0 b-2" v-if="props.row.shopRatings"><h3>评分:</h3> {{ props.row.shopRatings }}</p>
            <p m="t-0 b-2" v-if="props.row.shopGrade"><h3>等级:</h3> {{ props.row.shopGrade }}</p>
            <p m="t-0 b-2" v-if="props.row.averageShippingSpeed"><h3>平均发货速度:</h3> {{ props.row.averageShippingSpeed }}</p>
            <p m="t-0 b-2" v-if="props.row.afterSalesRate"><h3>售后率:</h3> {{ props.row.afterSalesRate }}</p>
            <p m="t-0 b-2" v-if="props.row.repurchaseRate"><h3>复购率:</h3> {{ props.row.repurchaseRate }}</p>
            <p m="t-0 b-2" v-if="props.row.mainBusiness"><h3>主营:</h3> {{ props.row.mainBusiness }}</p>
            <p m="t-0 b-2" v-if="props.row.shopDesc"><h3>介绍:</h3> {{ props.row.shopDesc }}</p>
            
            <h2>农产品</h2>
            <el-table :data="props.row.productTable" :border="childBorder">
              <el-table-column label="名称" prop="proName" />
              <el-table-column label="所属类别" prop="levelName" />
              <el-table-column label="价格" prop="price" sortable/>
              <el-table-column label="起批量" prop="startBatching" sortable/>
              <el-table-column label="收藏人数" prop="collectorsCounts" sortable/>
              <el-table-column label="采购热度" prop="purchasingHeat" sortable/>
              <el-table-column label="询价人数" prop="inquiry" sortable/>
              <el-table-column label="成交人数" prop="traded" sortable/>
              <el-table-column label="评价人数" prop="assess" sortable/>
              <el-table-column label="营业额" prop="turnover" sortable/>
              <el-table-column label="最后更新时间" prop="updateTime" sortable/>
              <el-table-column label="介绍" prop="proDesc">
                <template v-slot="{row}">
                  <el-tooltip
                  class="box-item"
                  effect="light"
                  :content="row.proDesc"
                  placement="top-end"
                >
                  <el-button type="success" round>介绍</el-button>
                </el-tooltip>
                </template>
              </el-table-column>
              <el-table-column label="详情">
                <template #default="props">
                  <a :href="props.row.proUrl" target="_blank">
                    <el-button
                        type="primary"
                    >详情</el-button>
                  </a>
                    </template>
              </el-table-column>
            </el-table>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="名称" prop="name" />
      <el-table-column label="总营业额" prop="cumulativeTurnover"  sortable='custom'/>
      <el-table-column label="粉丝数" prop="fansCounts"  sortable='custom'/>
      <el-table-column 
        label="地址" 
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
        { text: '台湾省', value: '34' }
      ]"
      :filter-method="filterAddressHandler"
        />
      <el-table-column 
        label="是否vip"
        :filters="[
          { text: '会员', value: '1' },
          { text: '普通', value: '0' },
        ]"
        :filter-method="filterIsVipHandler"
        >
        <template #default="scope">
        <el-tag
          :type="scope.row.isVip == 1 ? '' : 'success'"
          disable-transitions
          >{{ scope.row.isVip==1?'VIP':'普通' }}</el-tag
        >
      </template>
      </el-table-column>
      <el-table-column label="详情">
        <template #default="props">
          <a :href="props.row.shopUrl" target="_blank">
            <el-button
                type="primary"
            >详情</el-button>
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
        @size-change="paginationChange(currentPage,pageSize)"
        @current-change="paginationChange(currentPage,pageSize)"
      />
    </div>
  </div>
</template>

<script setup>
import { onBeforeMount, ref, toRaw, watch } from "vue";
import { $post, $get } from "../../utils/request";
import {
  sortChange,
  filterAddressHandler,
  filterIsVipHandler,
  searchByProName,
  paginationChange,
  toExcel,
  resetAddress,
  resetIsVip,
} from "../../js/table/shopTable";
// 导入全局状态管理
import { useStore } from "vuex";

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
  // 加载表格数据
  const result = await $post(
    "/table/shopTableInformation",
    toRaw(store.getters["shopTable/getQueryCriteria"])
  );
  tableData.value = result;
  // 用于存储对象的引用，以供更新数据的函数使用将新数据赋值给 tableData
  store.dispatch("shopTable/updateTableData", tableData);

  // 加载分页的数据
  total.value = result[0].totalCounts;
  // 用于存储对象的引用，以供更新数据的函数使用将新数据赋值给 total
  store.dispatch("shopTable/updateTotal", total);

  const btn = Array.from(document.getElementsByTagName("button"));
  // 添加事件（此时获取 12 个按钮，如果给 window 添加点击事件赋值，会获得 14 个按钮，
  //    新增分页中的前后按钮）
  btn[9].addEventListener("click", resetAddress);
  btn[11].addEventListener("click", resetIsVip);
});

// 实时更新输入框和 vuex 中的数据
watch(input, (newValue) => {
  // 更新 vux 中的数据
  store.dispatch("shopTable/updateQueryCriteria", {
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

// 农产品介绍弹出层的样式
.tooltip-base-box {
  width: 600px;
}
.tooltip-base-box .row {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.tooltip-base-box .center {
  justify-content: center;
}
.tooltip-base-box .box-item {
  width: 110px;
  margin-top: 10px;
}
</style>
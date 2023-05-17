// 导入 toRaw 函数
import { toRaw } from '@vue/reactivity'
import { xlsx } from "../../utils/xlsx"
// 导入全局状态管理
import store from '../../store/index'
import { $post } from '../../utils/request'


// 用于更新数据
export async function updateTableData() {
    // 更新表格数据
    const result = await $post("/table/shopTableInformation",
        toRaw(store.getters['shopTable/getQueryCriteria']))
    if (result.length == 0) {
         store.dispatch('shopTable/updateTableData', null)
         store.dispatch('shopTable/updateTotal', 0)
        return
    }
     store.dispatch('shopTable/updateTableData', result)
     store.dispatch('shopTable/updateTotal', result[0].totalCounts)
}

// 排序方法
export async function sortChange({ prop, order }) {
    // 更新 vux 中的数据
     store.dispatch('shopTable/updateQueryCriteria', {
        // 因为点击另外一个排序，上一个会失去焦点，因此置空
        cumulativeTurnover: null,
        fansCounts: null,
        [prop]: order
    })
    // 更新数据
     updateTableData()
}

// 地址选择
// 设置 flag 是为了让该函数只执行一次（数据过滤使用的方法， 如果是多选的筛选项，
//  对每一条数据会执行多次，任意一次返回 true 就会显示）
let addressFlag = false
// 是否是 VIP 选择
let isVipFlag = false
export async function filterAddressHandler(_, __, val) {
    if (addressFlag) {
        return
    }
    // 更新 vux 中的数据
     store.dispatch('shopTable/updateQueryCriteria', {
        provinceId: toRaw(val.filteredValue)
    })
    // 给 flag 重置
    addressFlag = true
    // 更新数据
    await updateTableData()
    // 加定时器是因为当两个多选框同时有值的时候，会一直请求
    setTimeout(() => {
        addressFlag = false
    }, 5000);
}
// 地址重置方法
export async function resetAddress() {
    // 更新 vux 中的数据
     store.dispatch('shopTable/updateQueryCriteria', {
        provinceId: []
    })
    // 更新数据
     updateTableData()
}

// 是否是 VIP 选择
export async function filterIsVipHandler(_, __, val) {
    if (isVipFlag) {
        return
    }
    // 更新 vux 中的数据
     store.dispatch('shopTable/updateQueryCriteria', {
        isVip: toRaw(val.filteredValue)
    })
    
    // 给 flag 重置
    isVipFlag = true
    // 更新数据
    await updateTableData()
    // 加定时器是因为当两个多选框同时有值的时候，会一直请求
    setTimeout(() => {
        isVipFlag = false
    }, 5000);
}
// 是否是 VIP 重置方法
export async function resetIsVip(){
    // 更新 vux 中的数据
     store.dispatch('shopTable/updateQueryCriteria', {
        isVip:[]
    })
    // 更新数据
     updateTableData()
}

// 通过商铺名称来进行模糊查询
export async function searchByProName(input) {
    // 更新 vux 中的数据，其他的筛选条件置空，不然报错
    if (input == '' || input == null) {
        // Uncaught (in promise) TypeError: Cannot read properties of null (reading 'filter')
         store.dispatch('shopTable/updateQueryCriteria', {
            input,
            cumulativeTurnover: null,
            fansCounts: null,
            currentPage: 1,
            pageSize: 6,
        })
    }
    // 更新数据
     updateTableData()
}

// 分页查找
export async function paginationChange(currentPage, pageSize) {
    // 更新 vux 中的数据
     store.dispatch('shopTable/updateQueryCriteria', {
        currentPage,
        pageSize
    })
    // 更新数据
     updateTableData()
}

// 商铺表的 excel 导出
export function toExcel(tableData) {
    // 组织导出数据的格式
    const excelData = tableData.map((item) => {
        return {
            name: item.name,
            cumulativeTurnover: item.cumulativeTurnover,
            fansCounts: item.fansCounts,
            address: item.address,
            isVip: item.isVip,
            shopUrl: item.shopUrl,
            shopHeat: item.shopHeat,
            shopRatings: item.shopRatings,
            shopGrade: item.shopGrade,
            averageShippingSpeed: item.averageShippingSpeed,
            afterSalesRate: item.afterSalesRate,
            repurchaseRate: item.repurchaseRate,
            mainBusiness: item.mainBusiness,
            shopDesc: item.shopDesc,
        };
    });
    // 表头信息
    const excelHeader = {
        name: "商铺名称",
        cumulativeTurnover: "总营业额",
        fansCounts: "粉丝数",
        address: "地址",
        isVip: "是否VIP",
        shopUrl: "商铺链接",
        shopHeat: "商铺热度",
        shopRatings: "商铺评分",
        shopGrade: "商铺等级",
        averageShippingSpeed: "平均发货速度",
        afterSalesRate: "售后率",
        repurchaseRate: "复购率",
        mainBusiness: "主营",
        shopDesc: "商铺介绍",
    };
    // 将数据导出成 excel 文件
    xlsx(excelData, excelHeader, "商铺信息");
}
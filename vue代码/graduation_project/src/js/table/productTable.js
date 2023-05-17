// 导入 toRaw 函数
import { toRaw } from '@vue/reactivity'
import { xlsx } from "../../utils/xlsx"
// 导入全局状态管理
import store from '../../store/index'
import { $post } from '../../utils/request'


// 用于更新数据
export async function updateTableData() {
    // 更新表格数据
    const result = await $post("/table/productTableInformation",
        toRaw(store.getters['productTable/getQueryCriteria']))
    if (result.length == 0) {
        store.dispatch('productTable/updateTableData', null)
        store.dispatch('productTable/updateTotal', 0)
        return
    }
    store.dispatch('productTable/updateTableData', result)
    store.dispatch('productTable/updateTotal', result[0].totalCounts)
}

// 排序方法
export async function sortChange({ prop, order }) {
    // 更新 vux 中的数据
    store.dispatch('productTable/updateQueryCriteria', {
        // 因为点击另外一个排序，上一个会失去焦点，因此置空
        price: null,
        collectorsCounts: null,
        turnover: null,
        [prop]: order
    })
    // 更新数据
    updateTableData()
}


// 发货地址选择
// 设置 flag 是为了让该函数只执行一次（数据过滤使用的方法， 如果是多选的筛选项，
//  对每一条数据会执行多次，任意一次返回 true 就会显示）
let flag = false
export async function filterHandler(_, __, val) {
    if (flag) {
        return
    }

    // 延时给 flag 重置
    flag = true
    // 更新 vux 中的数据
    store.dispatch('productTable/updateQueryCriteria', {
        shipFromAddress: toRaw(val.filteredValue)
    })

    // 更新数据
    await updateTableData()
    flag = false
}
// 发货地址重置方法
export async function resetShipFromAddress() {
    // 更新 vux 中的数据
    store.dispatch('productTable/updateQueryCriteria', {
        shipFromAddress: []
    })
    // 更新数据
    updateTableData()
}

// 通过农产品名称来进行模糊查询
export async function searchByProName(input) {
    // 更新 vux 中的数据，其他的筛选条件置空，不然报错
    if (input == '' || input == null) {
        // Uncaught (in promise) TypeError: Cannot read properties of null (reading 'filter')
        store.dispatch('productTable/updateQueryCriteria', {
            input,
            price: null,
            collectorsCounts: null,
            turnover: null,
            shipFromAddress: [],
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
    store.dispatch('productTable/updateQueryCriteria', {
        currentPage,
        pageSize
    })
    // 更新数据
    updateTableData()
}

// 农产品表的 excel 导出
export function toExcel(tableData) {
    // 组织导出数据的格式
    const excelData = tableData.map((item) => {
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
        proName: "农产品名称",
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
    xlsx(excelData, excelHeader, "农产品信息");
}
// 农产品表的筛选信息
export default {
    namespaced: true,
    state: {
        // 存储所有查询条件
        queryCriteria: {
            // 搜索输入值
            input: '',
            // 价格排序
            price: null,
            // 收藏人数排序
            collectorsCounts: null,
            // 营业额排序
            turnover: null,
            // 发货地址过滤
            shipFromAddress: [],
            // 当前页
            currentPage: 1,
            // 每页数量
            pageSize: 6,
        },
        // 用于存储对象的引用，以供更新数据的函数使用将新数据赋值给 tableData
        tableData: [],
        // 用于存储对象的引用，以供更新数据的函数使用将新数据赋值给 total
        total: null
    },
    getters: {
        // 获取所有查询条件
        getQueryCriteria(state) {
            return state.queryCriteria
        }
    },
    mutations: {
        // 更新所有查询条件
        updateQueryCriteria(state, aSubsetOfQueryCriteria) {
            // 先定义，防止 item is not defined
            let item
            // 替换搜索数据
            for (item in aSubsetOfQueryCriteria) {
                state.queryCriteria[item] = aSubsetOfQueryCriteria[item]
            }
        },
        // 更新存储对象的引用
        updateTableData(state, tableData) {
            state.tableData = tableData
        },
        // 更新存储对象的引用
        updateTotal(state, total) {
            state.total = total
        }
    },
    actions: {
        // 更新所有查询条件
        updateQueryCriteria(context, aSubsetOfQueryCriteria) {
            context.commit('updateQueryCriteria', aSubsetOfQueryCriteria)
        },
        // 更新存储对象的引用
        updateTableData(context, tableData) {
            context.commit('updateTableData', tableData)
        },
        // 更新存储对象的引用
        updateTotal(context, total) {
            context.commit('updateTotal', total)
        }
    },
    modules: {
    }
}
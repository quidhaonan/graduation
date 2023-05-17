import { createStore } from 'vuex'
// 引入农产品表的筛选信息
import productTable from './modules/productTable'
// 引入商铺表的筛选信息
import shopTable from './modules/shopTable'


export default createStore({
  modules: {
    productTable,
    shopTable
  }
})



// 创建项目自动有
// export default createStore({
//   state: {
//   },
//   getters: {
//   },
//   mutations: {
//   },
//   actions: {
//   },
//   modules: {
//   }
// })
import { createRouter, createWebHistory } from 'vue-router'
import Index from '../views/Index.vue'


const routes = [
  {
    path: '/',
    name: 'home',
    component:Index
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path:'/product',
    name:'product',
    component:() => import('../views/table/ProductTable.vue')
  },
  {
    path:'/shop',
    name:'shop',
    component:() => import('../views/table/ShopTable.vue')
  },
  // 出现错误跳转
  {
    path: '/error',
    name: 'Error',
    component: ()=>import('../views/Error404.vue')
  },
  // 错误页面（访问错误路径跳转）
  {
    path:'/:pathMatch(.*)*',
    name:'ErrorPage',
    meta:{ title:'404' },
    component:()=>import('../views/Error404.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
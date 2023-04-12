import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Index from '../views/Index.vue'
import App from '../App.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    // component: HomeView
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
    // component:() => import('../views/table/ShopTable.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

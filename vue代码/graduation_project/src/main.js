import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

const app=createApp(App)

// 加载 element-ui
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
app.use(ElementPlus)

// 全局挂载 axios
import axios from 'axios'
app.config.globalProperties.$axios = axios


app.use(store).use(router).mount('#app')

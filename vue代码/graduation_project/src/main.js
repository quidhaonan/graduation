import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'


const app = createApp(App)

// 加载 element-ui
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
app.use(ElementPlus)

// 设置全局错误处理
app.config.errorHandler = () => {
    // 跳转到错误页面
    // router.push({
    //     name: 'Error'
    // })
}

app.use(store).use(router).mount('#app')
// 导入加载显示
import { ElLoading } from 'element-plus'

// 导入 qs 库，是因为 post 请求带参数的时候 axios 会出现跨域（此时 get 请求并不会）
import qs from 'qs'
// 导入 axios
import axios from 'axios'

// 导入接口根地址
import {BASE_URL} from '../config/index'

// 初始化一个 axios 对象
const instance=axios.create({
    // 设置请求地址前缀
    baseURL:BASE_URL,
    // 请求超时时间
    timeout:5000,
})

// 定义 loading 的配置项
const options={
    text:'loading...',
    background:'Ivory'
}
let loadingInstance
// 添加请求拦截器
instance.interceptors.request.use((config)=>{
    loadingInstance = ElLoading.service(options)
    // 在发送请求之前做什么
    return config
},(err)=>{
    // 对请求错误做些什么
    return Promise.reject(err)
})

// 添加响应拦截器
instance.interceptors.response.use((response)=>{
    loadingInstance.close()
    // 对响应数据做点什么
    return response
},(err)=>{
    // 对响应错误做点什么
    return Promise.reject(err)
})

// 定义一个 get 请求方法
export const $get=async (url,params)=>{
    let {data}= await instance.get(url,{params})
    return data
}

// 定义一个 post 请求方法
export const $post=async (url, params)=>{
    // console.log(params)
    let {data}=await instance.post(url,qs.stringify(params))
    return data
}
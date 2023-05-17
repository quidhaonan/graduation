// 可定义多个地址
const model={
    // 本地接口地址
    local:'http://localhost:8080'
}

// 定义请求根路径
// 根据当前的开发阶段，返回对应的接口地址
export const BASE_URL=model.local
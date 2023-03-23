const { defineConfig } = require('@vue/cli-service')

// element-plus 的自动导入组件插件（使用全局导入，依赖已删除，因此注释）
// webpack.config.js
// const AutoImport = require('unplugin-auto-import/webpack')
// const Components = require('unplugin-vue-components/webpack')
// const { ElementPlusResolver } = require('unplugin-vue-components/resolvers')

module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,

  
  // 配置 webpack 插件
  // configureWebpack:{
  //   plugins:[
  //     AutoImport({
  //       resolvers: [ElementPlusResolver()],
  //     }),
  //     Components({
  //       resolvers: [ElementPlusResolver()],
  //     }),
  //   ]
  // },
})

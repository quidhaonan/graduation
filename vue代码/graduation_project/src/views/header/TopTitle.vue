<template>
  <h1 id="title"></h1>
</template>

<script setup>
  import '../../js/flexible'
  import { onMounted } from 'vue'
  import {initTitle} from '../../js/header/topTitle'

  onMounted(()=>{
    // const titleDom=document.getElementsByTagName('h1')[0]
    const titleDom=document.getElementById('title')
    initTitle(titleDom)

    // 浏览器大小改变后字体跟着变（目前没用）
    window.addEventListener('resize',()=>{
        const titleDom=document.getElementsByTagName('h1')[0]
        initTitle(titleDom)
    })
  })


</script>

<style lang='less' scoped>
  h1{
    width: 24rem;
    height: 1.25rem;
    // 38px
    font-size: 0.475rem;
    color: #fff;
    text-align: center;
    // 80px
    line-height: 1rem;
  }
</style>
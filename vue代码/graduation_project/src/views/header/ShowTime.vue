<template>
  <div id="showTime"></div>
</template>

<script setup>
let t=null
t=setTimeout(time, 1000)

function time(){
    clearTimeout(t)
    let dt=new Date()
    let y=dt.getFullYear()
    let mt=dt.getMonth()+1
    let day=dt.getDate()
    let h=dt.getHours()
    let m=dt.getMinutes()
    let s=dt.getSeconds()

    document.getElementById('showTime').innerHTML=
        '当前时间：'+
        y+
        '年'+
        mt+
        '月'+
        day+
        '-'+
        h+
        '时'+
        m+
        '分'+
        s+
        '秒'
    t=setTimeout(time,1000)
}
</script>

<style lang='less' scoped>
    #showTime{
        position: absolute;
        // 30px
        right: 0.375rem;
        top: 0;
        // 75px
        line-height: 0.9375rem;
        color: rgba(255, 255, 255, 0.7);
        // 20px
        font-size: 0.25rem;
    }
</style>
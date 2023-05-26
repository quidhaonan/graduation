<template>
  <div id="number">
    <div class="no-hd">
      <ul>
        <!-- <a href="product" target="_blank"><li id="productTotalCounts"></li></a> -->
        <router-link to="product" target="_blank" replace>
          <li ref="productTotalCounts" id="productTotalCounts"></li>
        </router-link>
        <router-link to="shop" target="_blank" replace
          ><li ref="shopTotalCounts" id="shopTotalCounts"></li
        ></router-link>
      </ul>
    </div>
    <div class="no-bd">
      <ul>
        <!-- <li>商品总数</li> -->
        <li>农产品总数</li>
        <li>商家总数</li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { initTotalCounts } from "../../js/middle/totalCounts";

const productTotalCounts = ref(null);
const shopTotalCounts = ref(null);

onMounted(() => {
  initTotalCounts(productTotalCounts.value, shopTotalCounts.value);
});
</script>

<style lang='less' scoped>
#number {
  background: rgba(101, 132, 226, 0.1);
  // 15px
  padding: 0.1875rem;
  .no-hd {
    position: relative;
    border: 1px solid rgba(25, 186, 139, 0.17);
    &::before {
      content: "";
      position: absolute;
      width: 30px;
      height: 10px;
      border-top: 2px solid #02a6b5;
      border-left: 2px solid #02a6b5;
      top: 0;
      left: 0;
    }
    &::after {
      content: "";
      position: absolute;
      width: 30px;
      height: 10px;
      border-bottom: 2px solid #02a6b5;
      border-right: 2px solid #02a6b5;
      right: 0;
      bottom: 0;
    }
    ul {
      display: flex;
      a {
        display: block;
        flex: 1;
        text-decoration: none;
        color: #000;
        li {
          position: relative;
          // flex: 1;
          text-align: center;
          // 80px
          height: 1rem;
          // 80px
          line-height: 1rem;
          // 70px
          font-size: 0.875rem;
          color: #ffeb7b;
          // 改变颜色，使图片打印后便于查看
          // color: black;
          // 4px
          padding: 0.05rem 0;
          font-family: electronicFont;
          font-weight: bold;
          &:first-child::after {
            content: "";
            position: absolute;
            height: 50%;
            width: 1px;
            background: rgba(255, 255, 255, 0.2);
            right: 0;
            top: 25%;
          }
        }
      }
    }
  }
  .no-bd ul {
    display: flex;
    li {
      flex: 1;
      // 40px
      height: 0.5rem;
      // 40px
      line-height: 0.5rem;
      text-align: center;
      // 18px
      font-size: 0.225rem;
      color: rgba(255, 255, 255, 0.7);
      // 10px
      padding-top: 0.125rem;
    }
  }
}
</style>
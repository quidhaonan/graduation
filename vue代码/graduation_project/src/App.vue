<template>
  <!-- <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
  </nav> -->
  <router-view />
</template>

<script setup>
// 解决 element-plus 的 el-table 在切换大小界面时候报错 ResizeObserver loop limit exceeded
function resolveResizeObserverLoopLimitExceeded() {
  const debounce = (fn, delay) => {
    let timer = null;
    return function () {
      let context = this;
      let args = arguments;
      clearTimeout(timer);
      timer = setTimeout(function () {
        fn.apply(context, args);
      }, delay);
    };
  };

  const _ResizeObserver = window.ResizeObserver;
  window.ResizeObserver = class ResizeObserver extends _ResizeObserver {
    constructor(callback) {
      callback = debounce(callback, 16);
      super(callback);
    }
  };
}
resolveResizeObserverLoopLimitExceeded();
</script>


<style lang="less">
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
body {
  background-color: rgba(216, 217, 219, 0.8);
  line-height: 1.15;
}

// 新建项目本来有的 css 样式
// #app {
//   font-family: Avenir, Helvetica, Arial, sans-serif;
//   -webkit-font-smoothing: antialiased;
//   -moz-osx-font-smoothing: grayscale;
//   text-align: center;
//   color: #2c3e50;
// }

// nav {
//   padding: 30px;

//   a {
//     font-weight: bold;
//     color: #2c3e50;

//     &.router-link-exact-active {
//       color: #42b983;
//     }
//   }
// }
</style>

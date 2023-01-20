<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      bgURL: [
        require("./assets/img/bg1.jpg"),
        require("./assets/img/bg2.jpg"),
        require("./assets/img/bg3.jpg"),
        require("./assets/img/bg4.jpg"),
      ],
    };
  },
};
</script>


<style>
/* 如果此处存在scoped则表示这里设置的样式,仅对本组件有效,不影响其它组件
    如果删除scoped则影响其它组件,这里由于是根组件,所以我们删除scoped设置全局css */
html,
body,
#app {
  /* 设置全局外边距为0 */
  margin: 0;
  /* 设置全局内边距为0 */
  padding: 0;
  /* 设置全局字体 */
  font-family: 喜鹊招牌体;
  /* 设置百分比高度的参照物 */
  height: 100%;
}

/*登录
*/
body,
html,
#app {
  
  height: 100%;
}
</style>
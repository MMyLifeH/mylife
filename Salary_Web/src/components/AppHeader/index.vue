<template>
  <el-header class="header" height="50px">
    <h3 class="title">
      企业工资管理系统
    </h3>
    <div>
      <el-button type="text" @click="signout" class="singout" style="color:#ecf5ff;font-family:喜鹊招牌体;font-size:17px">退出</el-button>
    </div>
  </el-header>
</template>

<script>
export default {
  data() {
    return {
      signOutConfirm: false,
    };
  },
  methods: {
    signout() {
      this.signOutConfirm = confirm("确认退出登录吗？");
      if (this.signOutConfirm) {
        this.$router.replace({ path: "/" });
      }
    },
  },
};
</script>

<style scoped>
/* 此处用来设置页眉样式,所以scoped不要删除,不能影响其它组件 */
/* 设置页眉 */
.header {
  /* 设置背景颜色 */
  background-color: #409eff;
  /* 设置字体颜色 */
  color: whitesmoke;
  /* 开启弹性盒子 */
  display: flex;
  /* 让页眉中的元素一左一右排列 这样 h3 在最左侧 div在最右侧*/
  justify-content: space-between;
  /* 垂直居中 */
  align-items: center;
}

/* 设置左侧标题 */
.header .title {
  /* 标题本身在最左侧,让它向右偏移40像素 */
  margin-left: 40px;
}
/* 设置标题中的logo信息 */
.header .title .logo {
  /* 图片宽度40像素 */
  width: 40px;
  /* 图片与文字垂直对齐 */
  vertical-align: middle;
}
/* 设置右侧的提示信息 */
/* 退出按钮 */
.singout {
  margin-right: 20px;
}
</style>
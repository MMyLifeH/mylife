<template>
  <div class="Home">
    <el-carousel height="500px" interval="2000">
      <el-carousel-item v-for="item in carouseData" :key="item">
        <img :src="item.url" alt="" />
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "home",
  components: {},
  data() {
    return {
      //定义跑马灯的图片路径
      carouseData: [
        { url: require("@/assets/img/1.png") },
        { url: require("@/assets/img/2.png") },
        { url: require("@/assets/img/3.png") },
        { url: require("@/assets/img/4.png") },
      ],
    };
  },
};
</script>

<style>
.Home {
  margin-top: 50px;
}
</style>
<template>
  <!--  账号输入登录-->
  <div class="loginOut">
    <el-carousel
      height="100%"
      class="login_carousel"
      direction="vertical"
      :autoplay="true"
    >
      <el-carousel-item v-for="item in bgURL" :key="item">
        <img class="login_bg" :src="item" alt="用户头像" />
      </el-carousel-item>
    </el-carousel>
    <router-view></router-view>
    <div class="login_content">
      <p><span class="left-quote"></span><span>山东理工大学</span></p>
      <p><span>厚德博学笃行至善</span><span class="right-quote"></span></p>
    </div>
    <p class="power_by">1956 - 2022</p>
    <div class="login_box">
      <router-link to="/auth">
        <div class="login_close"></div>
      </router-link>
      <div class="login_panel">
        <div class="login_title">
          <img src="../assets/img/logo.png" alt="" />
          <p>输入账号进行安全登录</p>
        </div>
        <label style="margin-top: 50px">账号：</label>
        <input
          v-model="userName"
          type="tel"
          pattern="^\d{11}$"
          title="请输入账号"
        />
        <label>密码：</label>
        <input v-model="userPassword" type="password" title="请输入密码" />
        <input class="bt" @click="login" type="submit" value="登录" />
        <el-button
          type="text"
          @click="register"
          class="register"
          style="color: #409eff; font-family: 喜鹊招牌体; font-size: 20px"
          >注册</el-button
        >
      </div>
    </div>
  </div>
</template>

<script>
import dao from "@/api/dao";

export default {
  name: "Input",
  data() {
    return {
      bgURL: [
        require("../assets/img/bg1.jpg"),
        require("../assets/img/bg2.jpg"),
        require("../assets/img/bg3.jpg"),
        require("../assets/img/bg4.jpg"),
      ],
      userName: "",
      userPassword: "",
    };
  },
  methods: {
    login() {
      dao
        .loginAdmin(this.userName, this.userPassword)
        .then((response) => {
               console.log(response);
          if (
            (response.data.data.name === this.userId,
            response.data.data.password === this.userPassword)
          ) {
       
            this.$message.success("登录成功");
            if(response.data.data.role==1){
             this.$router.replace({ path: "/adminHome" });
            }else{
            this.$router.replace({ path: "/home" });
            }
         
            sessionStorage.setItem('user',JSON.stringify(response.data.data)) 
          } else {
            this.$message.error("账号或密码有错，请检查后重新输入！");
            console.log(response.data);
          }
        })
        .catch((error) => {
          this.$message.error("登录失败，请检查账号或密码!");
          console.log(error);
        });
    },
    
    register() {
      this.$router.replace({ path: "/register" });
    },
  },
};
</script>

<style scoped>
/*登录框*/
.login_box {
  z-index: 99;
  position: absolute;
  width: 380px;
  height: 540px;
  top: 50%;
  left: 50%;
  margin-left: -190px;
  margin-top: -270px;
  border-radius: 6px;
  background-color: #fff;
  box-shadow: 0 2px 10px #999;
}

.login_close {
  position: absolute;
  top: 0;
  right: 0;
  width: 64px;
  height: 64px;
  background: url(../assets/img/qrcode.png) no-repeat right top;
  background-size: 100% 100%;
  border-top-right-radius: 5px;
  cursor: pointer;
  z-index: 99;
}

/*登录*/
.login_panel {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 270px;
  height: 540px;
  padding: 0 55px;
  transform: translate(-50%, -50%);
  /* background: #fff; */
  border-radius: 6px;
  overflow: hidden;
}

.login_panel .login_title {
  text-align: center;
}

.login_panel .login_title img {
  margin-top: 60px;
  height: 70px;
  width: 70px;
  border-radius: 50%;
  padding: 10px;
  border: 3px solid #d7e8fc;
}

.login_panel .login_title p {
  margin-top: 15px;
  color: #999999;
  font-size: 15px;
}

.login_panel label {
  display: block;
  font-size: 12px;
  line-height: 18px;
  color: #a9a8a5;
  margin-top: 10px;
}

.login_panel input {
  display: inline;
  height: 42px;
  padding: 0 5%;
  line-height: 42px;
  font-size: 14px;
  color: #333333;
  border-radius: 4px;
  outline: 0;
  border: 0;
  width: 90%;
  background: #d7e8fc;
}

/* 按钮 */
.login_panel .bt {
  margin-top: 35px;
  width: 100%;
  color: #ffffff;
  background: #379df6;
  cursor: pointer;
}

.login_panel .bt:hover {
  background-color: #2f86f6;
}

/*登录背景幻灯片*/
.login_carousel {
  height: 100%;
}

.login_carousel .login_bg {
  height: 100%;
  animation: unfocus 500ms forwards;
}

.login_carousel .login_bg:hover {
  animation: focus 500ms forwards;
}

@keyframes unfocus {
  0% {
    transform: scale(1.05);
  }
  100% {
    transform: scale(1);
  }
}

@keyframes focus {
  0% {
    transform: scale(1);
  }
  100% {
    transform: scale(1.05);
  }
}

/*登录文字*/
.login_content {
  z-index: 99;
  position: absolute;
  top: 80px;
  font-size: 45px;
  color: #fff;
}

.login_content p {
  position: relative;
  padding: 10px 100px;
}

.login_content .left-quote {
  background: url("../assets/img/leftquote.png") top no-repeat;
  display: inline-block;
  position: absolute;
  left: 40px;
  top: 8px;
  height: 32px;
  width: 46px;
  overflow: hidden;
}

.login_content .right-quote {
  background: url("../assets/img/rightquote.png") no-repeat top;
  position: absolute;
  right: 40px;
  top: 20px;
  height: 32px;
  width: 46px;
  overflow: hidden;
}
.loginOut {
  background-size: cover;
  height: 100%;
  width: 100%;
}

/*版权信息*/
.power_by {
  position: absolute;
  bottom: 5px;
  right: 50px;
  color: #d3d3d3;
  font-size: 20px;
  line-height: 1.6;
  z-index: 98;
}
/* 注册 */
.register {
  margin-top: 20px;
  margin-left: 84%;
}
</style>
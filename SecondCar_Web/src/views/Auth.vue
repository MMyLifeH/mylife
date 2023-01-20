<template>
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
    <p class="power_by">1956 - 2022</p>

    <!-- 扫码登录 -->
    <div class="login_box">
      <router-link to="/input">
        <div class="login_close"></div>
      </router-link>
      <div class="qrcode">
        <img
          class="img"
          :src="imgURL"
          alt="登录码"
          v-show="state === 1 || state === 3"
        />
        <div class="empty" v-show="state === 0"></div>
        <div class="refresh" v-show="state === 3">
          <i class="refresh_mask"></i>
          <i class="refresh_icon" @click="getToken"></i>
        </div>
        <div class="result" v-show="state === 2">
          <img class="u_avatar" :src="userAvatar" alt="用户头像" />
          <p class="u_name">{{ userName }}</p>
        </div>
        <div>
          <p class="sub_title">{{ tip }}</p>
          <p class="sub_desc">扫码登录，更易、更快、更安全</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import dao from "@/api/dao";

export default {
  name: "Auth",
  data() {
    return {
      bgURL: [
        require("../assets/img/bg1.jpg"),
        require("../assets/img/bg2.jpg"),
        require("../assets/img/bg3.jpg"),
        require("../assets/img/bg4.jpg"),
      ],
      state: 0, // 场景：0无登录码，1有登陆码，2正在登录，3登录码过期
      count: 10, // 登录码有效倒计时（S）
      tip: "正在获取登录码，请稍等", // 提示
      imgURL: "", // 登录码路径
      authToken: "", // 验证口令
      userId: "", // 扫码登录的用户ID
      userAvatar: "", // 扫码登录的用户头像
      userName: "",
      authState: "", // 扫码登录的用户名
      // 获取口令
      tokenImgApi: "http://localhost:9999/users/auth/img/", // 获取口令对应的登录码
      // 获取口令信息
    };
  },
  created() {
    this.getToken();
  },
  methods: {
    getToken() {
      console.log("开始获取");
      // 所有参数重置
      this.state = 0; // 场景为无二维码
      this.tip = "正在获取登录码，请稍等";
      this.count = 30;
      clearInterval(this.timeCount);
      // 开始获取新的token
      // 获取口令的API
      dao
        .tokenApi()
        .then((response) => {
          // 保存token，改变场景，显示登录码，开始轮询
          this.authToken = response.data.data;
          this.state = 1; // 场景为有登录码
          this.tip = "请使用手机口令扫码登录";
          this.imgURL = this.tokenImgApi + response.data.data; // 拼装获得登录码链接
          // this.timeCount = setInterval(this.getTokenInfo, 1000); // 开启每隔1S的轮询，向服务器请求口令信息
        })
        .catch((error) => {
          console.log(error);
          this.getToken();
        });
    },
    getTokenInfo() {
      // 登录码有效时间减少
      this.count--;
      // 登录码到期，改变场景
      if (this.count === 0) {
        this.state = 3; // 场景为登录码过期
        this.tip = "二维码已过期，请刷新";
      }
      // 防止计数溢出
      if (this.count < -1000) {
        this.count = -1;
      }
      // 轮询查询token状态
      dao
        .tokenInfoApi(this.authToken, this.userId, this.authState)
        .then((response) => {
          let auth = response.data;
          // token状态为登录成功
          if (auth.authState === 1) {
            this.$message({
              message: "登录成功！",
              type: "success",
            });
            clearInterval(this.timeCount); // 关闭轮询，溜了
          }
          // token状态为正在登陆，改变场景，请求扫码用户信息
          else if (auth.authState === 2) {
            this.userId = auth.userId;
            this.getUserInfo();
            this.state = 2;
            this.tip = "扫码成功，请在手机上确认";
            // token状态为过期（服务器），改变场景
          } else if (auth.authState === 3) {
            this.state = 3;
            this.tip = "二维码已过期，请刷新";
          }
        })
        .catch((error) => {
          // console.log(error);
        });
    },
    getUserInfo() {
      dao
        .userInfoApi(this.userId)
        .then((response) => {
          // 获取用户信息，并进行显示
          this.userName = response.data.data.userName;
          this.userAvatar = response.data.data.userAvatar;
          console.log(response.data.data);
        })
        .catch((error) => {
          // console.log(error);
        });
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
  background: url(../assets/img/pcinput.png) no-repeat right top;
  background-size: 100% 100%;
  border-top-right-radius: 5px;
  cursor: pointer;
  z-index: 99;
}
/* 背景 */
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
.login_content .left-quote {
  background: url("../assets/img/leftquote.png") top no-repeat;
  display: inline-block;
  position: absolute;
  left: 20px;
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
.login_content {
  z-index: 99;
  position: absolute;
  top: 80px;
  font-size: 45px;
  color: #fff;
}

/*二维码*/
.qrcode {
  position: relative;
  text-align: center;
}

/* 二维码获取 */
.qrcode .img {
  display: block;
  width: 240px;
  height: 240px;
  margin: 70px auto 25px;
}

.qrcode .sub_title {
  text-align: center;
  font-size: 19px;
  color: #353535;
  margin-bottom: 23px;
}

.qrcode .sub_desc {
  text-align: center;
  color: #a3a3a3;
  font-size: 14px;
  padding: 0 40px;
  line-height: 1.8;
}

/* 二维码为空 */
.qrcode .empty {
  display: block;
  width: 240px;
  height: 240px;
  margin: 70px auto 25px;
  background: #d7e8fc;
}

/* 二维码刷新 */
.qrcode .refresh {
  display: block;
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 240px;
}

.qrcode .refresh .refresh_mask {
  position: absolute;
  left: 50%;
  top: 50%;
  margin-left: -120px;
  margin-top: -120px;
  width: 240px;
  height: 240px;
  background: #ffffffe0;
}

.qrcode .refresh .refresh_icon {
  position: absolute;
  left: 50%;
  top: 50%;
  margin-left: -48px;
  margin-top: -48px;
  height: 96px;
  width: 96px;
  cursor: pointer;
  background: url(../assets/img/refresh.png) no-repeat;
}

.qrcode .refresh .refresh_icon:hover {
  animation: refresh 1s linear infinite;
}

@keyframes refresh {
  0% {
    transform: rotate(0deg);
  }

  100% {
    transform: rotate(360deg);
  }
}

/* 二维码结果 */
.qrcode .result {
  display: block;
  width: 240px;
  height: 240px;
  margin: 70px auto 25px;
}

.qrcode .result .u_avatar {
  height: 150px;
  width: 150px;
  margin-top: 15px;
  border-radius: 5px;
  box-shadow: 0 2px 10px #999;
  -moz-box-shadow: #999 0 2px 10px;
  -webkit-box-shadow: #999 0 2px 10px;
}

.qrcode .result .u_name {
  text-align: center;
  font-size: 19px;
  color: #353535;
  margin-top: 20px;
}
</style>

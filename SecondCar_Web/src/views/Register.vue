<template>
  <!--  账号输入登录-->
  <div class="Register">
    <el-carousel
      height="100%"
      class="register_carousel"
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
          <p>安全注册</p>
        </div>
        <el-form :model="addform" :rules="rules" ref="addform">
         <el-form-item prop="name">
            <label style="margin-top: 10px">账号：</label>
            <input
              v-model="addform.name"
              type="tel"
              pattern="^\d{11}$"
              title="请输入用户名"
            />
        </el-form-item>
          <el-form-item prop="password">
        <label>密码：</label>
        <input v-model="addform.password" type="password" title="请输入密码" />
        </el-form-item>
          <el-form-item prop="phone">
        <label>电话：</label>
          <input v-model="addform.phone" type="text" title="请输入电话" />
        </el-form-item>
          <el-form-item prop="email">
        <label>邮箱：</label> 
        <input v-model="addform.email" type="text" title="请输入邮箱" />
        </el-form-item>
          <el-form-item prop="address">
        <label>地址：</label>
        <input v-model="addform.address" type="text" title="请输入地址" />
          </el-form-item>
        </el-form>
        <input class="bt" @click="adduser('addform')" type="submit" value="注册" />
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
       addform: {
        name: "",
        password: "",
        phone: "",
        email: "",
        address:"",
        // pic: "",
      },

      rules:{
        name:{
          required: true, message: '请输入用户名', trigger: 'blur',
        },
        password:{
          required: true, message: '请输入密码', trigger: 'blur',
        },
        phone:{
          required: true, message: '请输入电话', trigger: 'blur',
        },
        email:{
          required: true, message: '请输入邮箱', trigger: 'blur',
        },
        address:{
          required: true, message: '请输入地址', trigger: 'blur',
        }
      }
      // pic: "",
    };
  },
  methods: {
     adduser(addForm) {
      this.$refs[addForm].validate((valid) => {
        if (valid) {
          this.$http.post("/login/adduser", this.addform)
          this.$message.success("注册成功")
          this.$refs[addForm].resetFields()
          this.$router.replace({path:'\input'})
          console.log(this.data)
        } else {
          this.$message.error("有漏填项，请检查后重新输入！")
        }
        
      });
    },
  },
};
</script>

<style scoped>
/*登录框*/
.el-form-item{
  margin: 0px;
}
/deep/ .el-form-item__content{
 line-height: 0px;
}
/deep/ .el-form-item__error{
  left: auto;
  right: 0;
}
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
  margin-top: 10px;
  height: 70px;
  width: 70px;
  border-radius: 50%;
  padding: 10px;
  border: 3px solid #d7e8fc;
}

.login_panel .login_title p {
  margin-top: 10px;
  color: #999999;
  font-size: 15px;
}

.login_panel label {
  display: block;
  font-size: 12px;
  line-height: 16px;
  color: #a9a8a5;
  margin-top: 10px;
}

.login_panel input {
  display: inline;
  height: 36px;
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
  margin-top: 22px;
  width: 100%;
  color: #ffffff;
  background: #379df6;
  cursor: pointer;
}

.login_panel .bt:hover {
  background-color: #2f86f6;
}

/*登录背景幻灯片*/
.register_carousel {
  height: 100%;
}

.register_carousel .login_bg {
  height: 100%;
  animation: unfocus 500ms forwards;
}

.register_carousel .login_bg:hover {
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
.Register {
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
.gologin {
  margin-top: -10px;
  margin-left: 78%;
}
</style>
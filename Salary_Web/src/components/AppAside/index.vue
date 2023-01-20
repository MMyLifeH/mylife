<template>
    <el-aside class="aside" width="200px">
        <!-- 此处使用了ElementUI的菜单栏 组件 
            background-color="#545c64"  菜单栏背景色
            text-color:菜单栏字体颜色
            active-text-color="#ff770f" 链接被激活时,或者说浏览器地址栏的哈希匹配时
            字体颜色
            :router="true" 激活路由 功能同 router-link
            :unique-opened="true" 同时只能激活一个菜单

        -->
        <el-menu background-color="#545c64" style="height:100%" text-color="#fff" active-text-color="#ff770f"
            :router="true" :unique-opened="true">
            <el-menu-item index="/home1" v-if="user.role === 0" >
                <i class="el-icon-s-home"></i>
                <span slot="title">主页</span>
            </el-menu-item>
            <el-menu-item index="/home2" v-if="user.role === 1" >
                <i class="el-icon-s-home"></i>
                <span slot="title">主页</span>
            </el-menu-item>
            <el-menu-item index="/report"  v-if="user.role === 2">
                <i class="el-icon-s-marketing"></i>
                <span slot="title">统计报表</span>
            </el-menu-item>
            <el-menu-item index="/reimburse"  v-if="user.role === 0">
                <i class="el-icon-s-order"></i>
                <span slot="title">报销管理</span>
            </el-menu-item>

            <el-menu-item index="/salary"  v-if="user.role === 2">
                <i class="el-icon-tickets"></i>
                <span slot="title">工资管理</span>
            </el-menu-item>

            <el-menu-item index="/reviews"  v-if="user.role === 2">
                <i class="el-icon-s-claim"></i>
                <span slot="title">报销审核</span>
            </el-menu-item>
            <el-menu-item index="/Umanage"  v-if="user.role === 1">
                <i class="el-icon-s-custom"></i>
                <span slot="title">用户管理</span>
            </el-menu-item>
            
            <el-menu-item index="/my" v-if="user.role === 0">
                <i class="el-icon-user-solid"></i>
                <span slot="title">我的信息</span>
            </el-menu-item>

        </el-menu>
    </el-aside>
</template>

<script>
export default {
    data(){
        return{
            user:{
            }
        }
    },
    created(){
        let user =  JSON.parse(sessionStorage.getItem('user'))
        if(user != null){
            this.user = user
        }
    }
}
</script>

<style scoped>
.aside {
    background-color: #545c63;
    color: whitesmoke;
    display: flex;
    justify-content: center;
    align-items: center;
    /* 如果内部元素尺寸溢出隐藏滚动条 */
    overflow: hidden;
}

.aside .el-menu {
    width: 100%;
    text-align: center;
    border-right: none;
}
</style>
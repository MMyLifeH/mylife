<template>
    <el-container class="section">
        <!-- 引用侧边栏 -->
        <app-aside></app-aside>
        <!-- 引用主页 -->
        <app-main></app-main>
    </el-container>
</template>

<script>
/* 导入侧边栏 */
import AppAside from '../AppAside'
/* 导入主页 */
import AppMain from '../AppMain'

export default {
    /* 注册子组件 */
    components:{
        AppAside,
        AppMain,
    }
}
</script>

<style scoped>
    .section{
        height: 100%;
    }
</style>
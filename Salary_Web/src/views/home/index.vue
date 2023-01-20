<template>
    <div class="home">
         <el-container class="layout-container" direction="vertical">
                <!-- 引用页眉 -->
                <app-header></app-header>
                <!-- 引用主体 -->
                <app-section></app-section>
                <!-- 引用页脚 -->
                <app-footer></app-footer>
            </el-container>
    </div>
    
</template>
<script>
/* 
    导入页眉 主体 页脚组件 
    @:表示从 src 目录下寻找 脚手架中使用
    只要文件为 index.xx 则仅仅需要导入包(文件夹)即可
*/
import AppHeader from '@/components/AppHeader'
import AppSection from '@/components/AppSection'
import AppFooter from '@/components/AppFooter'

    export default{
         /* 注册子组件 */
        components:{
            /* 注册页眉 */
            AppHeader,
            /* 主页主体 */
            AppSection,
            /* 注册页脚 */
            AppFooter,
        },
        data(){
            return{

            }
        },
        methods:{

        },
        created(){

        }
    }
</script>
<style scoped>
    .home{
        height: 100%;
    }
</style>
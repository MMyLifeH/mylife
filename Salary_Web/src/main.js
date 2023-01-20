/* 导入Vue依赖,在非模块化开发中 <script src="../node_modules/vue/dist/vue.js">
如果导入的是 .js 则 .js后缀省略 */
import Vue from "vue";
/* 导入根组件 */
import App from "./App.vue";
/* 导入路由表 注意如果文件为 index.xxx 那么 可以只导入包名 即可,自动导入内侧的index.xxx */
import router from "./router";
/* 引入ElementUI依赖 */
import ElementUI from 'element-ui';
import axios from 'axios'
/* 引入ElementUI的全局css文件 */
import 'element-ui/lib/theme-chalk/index.css';
// 引入 echarts
import * as echarts from 'echarts'
/* Vue加载第三方UI插件 */
Vue.use(ElementUI);

/* 关闭提示 */
Vue.config.productionTip = false;

axios.defaults.baseURL = "http://localhost:9999/users"

Vue.prototype.$http = axios
import '@/assets/et.css'
Vue.prototype.$echarts = echarts

new Vue({
    /* 加载路由表 */
    router,
    /* 这里效果等同于 template:'<app />' 将根组件渲染到页面中 取代 $mount() 加载的
    模板 这里也就是覆盖了 <div id="app"></div> */
    render: (h) => h(App),
}).$mount("#app");

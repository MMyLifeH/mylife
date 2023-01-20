/* 导入Vue依赖 */
import Vue from "vue";
/* 导入VueRouter依赖 */
import VueRouter from "vue-router";
/* Vue加载路由依赖 */
Vue.use(VueRouter);

/* 设置路由 */
const routes = [
  
    {
        /* 监听到 #/table */
        path:'/adminHome',
        /* 
            显示的组件 
            此处使用了懒加载的方式来加载组件
        */
        component:()=>import('@/views/home'),
        redirect:'/report',
        children:[
            {
                path: '/vehicle',
                component: () => import('@/views/vehicle')
            },
            {
                path:'/reviews',
                component:()=>import('@/views/reviews')
            },
            {
                path: '/order',
                component: () => import('@/views/order')
            },
            {
                path: '/Umanage',
                component: () => import('@/views/Umanage')
            },
            {
                path: '/report',
                component: () => import('@/views/report')
            },
        ]
    },
    {
        path:'/home',
        component: () => import('@/views/home'),
        redirect: '/home1',
        children:[
            {
                path: '/home1',
                component: () => import('@/views/home1')
            },
            {
                path: '/carv',
                component: () => import('@/views/carv')
            },
            {
                path: '/shop',
                component: () => import('@/views/shop')
            },
            {
                path: '/collect',
                component: () => import('@/views/collect')
            },
            {
                path: '/qreview',
                component: () => import('@/views/qreview')
            },
        ]
    },
    {
        path: '/',
        component: () => import('@/views/Input')
    },
    {
        path: '/auth',
        component: () => import('@/views/Auth')
    },
    {
        path: '/input',
        component: () => import('@/views/Input')
    },
    {
        path: '/register',
        component: () => import('@/views/Register')
    },

];

const router = new VueRouter({
    routes,
});

export default router;

/* 导入Vue依赖 */
import Vue from "vue";
/* 导入VueRouter依赖 */
import VueRouter from "vue-router";
/* Vue加载路由依赖 */
Vue.use(VueRouter);

/* 设置路由 */
const routes = [
    {
        path:'/userHome',
        component: () => import('@/views/home'),
        redirect: 'home1',
        children:[
            {
                path: '/home1',
                component: () => import('@/views/home1')
            },
            {
                path: '/my',
                component: () => import('@/views/my')
            },
            {
                path: '/reimburse',
                component: () => import('@/views/reimburse')
            },
            
        ]
    },
    {
        /* 监听到 #/table */
        path:'/adminHome',
        /* 
            显示的组件 
            此处使用了懒加载的方式来加载组件
        */
        component:()=>import('@/views/home'),
        redirect: 'home2',
        children:[
            {
                path: '/home2',
                component: () => import('@/views/home2')
            },
            
            {
                path: '/Umanage',
                component: () => import('@/views/Umanage')
            },
            
        ]
    },
    {
        /* 监听到 #/table */
        path:'/financeHome',
        /* 
            显示的组件 
            此处使用了懒加载的方式来加载组件
        */
        component:()=>import('@/views/home'),
        redirect: 'report',
        children:[
            {
                path: '/report',
                component: () => import('@/views/report')
            },
            {
                path: '/reviews',
                component: () => import('@/views/reviews')
            },
            {
                path: '/salary',
                component: () => import('@/views/salary')
            },
        ]
    },
    {
        path: '/',
        component: () => import('@/views/Input')
    },
    {
        path: '/input',
        component: () => import('@/views/Input')
    },

];

const router = new VueRouter({
    routes,
});

export default router;

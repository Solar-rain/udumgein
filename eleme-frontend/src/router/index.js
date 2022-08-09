import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

import TabBar from '@/components/TabBar'

const routes = [
    {
        path: '/404',
        component: () => import('@/views/404'),
    },
    {
        path: '/login',
        redirect: '/login/email',
    },
    {
        path: '/login/email',
        component: () => import('@/views/Login/LoginWithCode'),
    },
    {
        path: '/login/pwd',
        component: () => import('@/views/Login/LoginWithPwd'),
    },
    {
        path: '/',
        component: TabBar,
        redirect: '/home',
        children: [
            {
                path: 'home',
                name: '主页',
                component: () => import('@/views/Home'),
            },
            {
                path: 'order',
                name: '订单页',
                component: () => import('@/views/Order'),
            },
            {
                path: 'profile',
                name: '个人中心',
                component: () => import('@/views/Profile'),
            }
        ]
    },
    // {path: '*', redirect: '/404'}
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router

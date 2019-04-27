import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/dashboard'
        },
        {
            path: '/',
            component: resolve => require(['../components/common/Home.vue'], resolve),
            meta: { title: '自述文件' },
            children:[
                {
                    path: '/dashboard',
                    component: resolve => require(['../components/page/Dashboard.vue'], resolve),
                    meta: { title: '系统首页' }
                },
                {
                    path: '/userMng',
                    component: resolve => require(['../components/page/UserMng.vue'], resolve),
                    meta: { title: '用户管理' }
                },
                {
                    path: '/deptMng',
                    component: resolve => require(['../components/page/DeptMng.vue'], resolve),
                    meta: { title: '机构管理' }
                },
                {
                    path: '/menuMng',
                    component: resolve => require(['../components/page/MenuMng.vue'], resolve),
                    meta: { title: '菜单管理' }
                },
                {
                    path: '/roleMng',
                    component: resolve => require(['../components/page/RoleMng.vue'], resolve),
                    meta: { title: '角色管理' }
                },
                {
                    path: '/permisMng',
                    component: resolve => require(['../components/page/PermisMng.vue'], resolve),
                    meta: { title: '角色管理' }
                },
                {
                    path: '/logMng',
                        component: resolve => require(['../components/page/LogMng.vue'], resolve),
                    meta: { title: '系统日志' }
                },
                {
                    path: '/404',
                        component: resolve => require(['../components/page/404.vue'], resolve),
                    meta: { title: '404' }
                },
                {
                    path: '/403',
                        component: resolve => require(['../components/page/403.vue'], resolve),
                    meta: { title: '403' }
                }
            ]

        },
        {
            path: '/login',
            component: resolve => require(['../components/page/Login.vue'], resolve)
        },
        {
            path: '*',
            redirect: '/404'
        }
    ]
})

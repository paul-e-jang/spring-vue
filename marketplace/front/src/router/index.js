import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import LoginPage from '@/views/LoginPage'
import RegisterPage from '@/views/RegisterPage'
import UserDetail from '@/views/UserDetail'
import UserList from '@/views/UserList'
import UserCreate from '@/views/UserCreate'
import WriteArticle from '@/views/WriteArticle'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/top',
    component: Home
  },
  {
    path: '/users',
    component: UserList
  },
  {
    path: '/users/new',
    component: UserCreate
  },
  {
    path: '/users/detail',
    component: UserDetail
  },
  {
    path: '/logout',
    beforeEnter: function (to, from, next) {
      next('/')
    }
  },
  {
    path: '/WriteArticle',
    component: WriteArticle
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/loginpage',
    name: 'LoginPage',
    component: LoginPage
  },
  {
    path: '/registerpage',
    name: 'RegisterPage',
    component: RegisterPage
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginPage from '@/views/LoginPage'
import RegisterPage from '@/views/RegisterPage'


Vue.use(VueRouter)

const routes = [{
  path: '/login',
  name: 'loginPage',
  component: LoginPage
},{
  path: '/register',
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

import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import vuetify from './plugins/vuetify'
/*
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
*/

// axios bootstrap

axios.defaults.baseURL = '/api'
axios.defaults.headers.common.Accept = 'application/json'
axios.interceptors.response.use(
  response => response,
  (error) => {
    return Promise.reject(error)
  }
)

Vue.config.productionTip = false
/*
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
*/

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')

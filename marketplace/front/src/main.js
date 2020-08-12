import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import vuetify from './plugins/vuetify'
import 'vuetify/dist/vuetify.min.css'
import Vuelidate from 'vuelidate'
import eventBus from './event-bus'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import '@babel/polyfill'

// axios bootstrap

Vue.use(BootstrapVue)
Vue.use(Vuelidate)
Vue.use(IconsPlugin)
Vue.use(vuetify)

axios.defaults.baseURL = '/api'
axios.defaults.headers.common.Accept = 'application/json'
axios.interceptors.response.use(
  response => response,
  (error) => {
    return Promise.reject(error)
  }
)

Vue.config.productionTip = false
Vue.prototype.$bus = eventBus

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')

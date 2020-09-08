import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import 'bootstrap/dist/css/bootstrap.min.css'
import vuetify from './plugins/vuetify'
import Vuetify from 'vuetify/lib'
import Vuelidate from 'vuelidate'
import eventBus from './event-bus'
import '@babel/polyfill'

import CKEditor from '@ckeditor/ckeditor5-vue'

// axios bootstrap

Vue.use(Vuelidate)
Vue.use(Vuetify)
Vue.use(CKEditor)

export default new Vuetify({
  icons: {
    iconfont: 'mdiSvg'
  }
})

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

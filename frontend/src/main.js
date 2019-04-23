// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Vuex from 'vuex'
import App from './App'
import router from './router'
import axios from 'axios'
import VueCookie from 'vue-cookies'
import store from './store'

Vue.config.productionTip = false
Vue.prototype.$http = axios
Vue.use(VueCookie)
Vue.use(Vuex)

/* eslint-disable no-new */
new Vue({
  store,
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})

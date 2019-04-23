import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    count: 0,
    token: '',
    user: {}
  },
  getters: {
    getCountValue: state => {
      return state.count
    },
    getToken: state => {
      return state.token
    },
    getUser: state => {
      return state.user
    },
    isloggedIn: state => {
      return !!state.user
    }
  },
  mutations: {
    increment (state) {
      state.count++
    },
    setToken (state, token) {
      state.token = token
    },
    setUser (state, user) {
      state.user = user
    }
  },
  actions: {
    getUserFromServer (context) {
      if (context.state.token) {
        axios.get('/api/users', {
          headers: {
            Authorization: 'Bearer ' + context.state.token // the token is a variable which holds the token
          }
        }).then((resp) => {
          context.commit('setUser', resp.data)
        }, (resp) => {
          console.log('error')
          window.$cookies.remove('FootballDiary')
          context.commit('setUser', '')
        })
      }
    }
  }
})

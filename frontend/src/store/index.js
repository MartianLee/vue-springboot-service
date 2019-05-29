import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    count: 0,
    token: '',
    user: {},
    log: []
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
    getLog: state => {
      return state.log
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
    },
    setLog (state, log) {
      state.log = log
    },
    logout (state) {
      state.user = ''
      state.token = ''
    }
  },
  actions: {
    async getUserFromServer (context) {
      if (context.state.token) {
        axios.get('/api/users', {
          headers: {
            Authorization: 'Bearer ' + context.state.token // the token is a variable which holds the token
          }
        }).then((resp) => {
          console.log('getUserFromServer.action.afterAxios')
          context.commit('setUser', resp.data)
        }, (resp) => {
          console.log('Error while loading user')
          window.$cookies.remove('FootballDiary')
          context.commit('setUser', '')
        })
      }
    },
    async getLogById ({commit, state}) {
      console.log('getUserLogById.action')
      if (state.user.id) {
        axios.get(`/api/diary/?id=${state.user.id}`, {
          headers: {
            Authorization: 'Bearer ' + state.token // the token is a variable which holds the token
          }
        }).then((resp) => {
          commit('setLog', resp.data)
        }, (resp) => {
          console.log('Error while loading log ')
        })
      }
    }
  }
})

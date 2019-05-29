<template>
  <v-app>
    <!-- <nav-drawer></nav-drawer> -->
    <nav-bar-top></nav-bar-top>
    <nav-bar></nav-bar>
    <v-content class="content">
      <v-container fluid>
        <router-view></router-view>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
import NavBar from './components/NavBar.vue'
import NavBarTop from './components/NavBarTop.vue'
import NavDrawer from './components/NavDrawer.vue'

export default {
  name: 'App',
  components: {
    'nav-bar': NavBar,
    'nav-bar-top': NavBarTop,
    'nav-drawer': NavDrawer
  },
  created () {
    console.log('App.vue')
    let token = window.$cookies.get('FootballDiary')
    if (token) {
      this.$store.commit('setToken', token)
      console.log('App.vue.afterSetToken')
      this.$store.dispatch('getUserFromServer')
    } else {
      this.onLogout()
    }
  },
  methods: {
    onLogout: function () {
      this.$store.commit('logout')
      window.$cookies.remove('FootballDiary')
      this.$router.push('/')
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
.content {
  margin-top: 56px;
  margin-bottom: 56px;
}
.logo {
  width: 12vw;
  margin-right: 1vw;
}
</style>

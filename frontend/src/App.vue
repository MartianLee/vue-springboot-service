<template>
  <v-app>
    <!-- <nav-drawer></nav-drawer> -->
    <nav-bar></nav-bar>
    <v-content>
      <v-container fluid>
        <router-view></router-view>
      </v-container>
    </v-content>
    <v-footer app></v-footer>
  </v-app>
</template>

<script>
import NavBar from './components/NavBar.vue'
import NavDrawer from './components/NavDrawer.vue'

export default {
  name: 'App',
  components: {
    'nav-bar': NavBar,
    'nav-drawer': NavDrawer
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
.logo {
  width: 12vw;
  margin-right: 1vw;
}
</style>

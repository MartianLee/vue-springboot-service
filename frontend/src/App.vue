<template>
  <div id="app">
    <nav-bar></nav-bar>
    <img class="logo" src="/static/images/spring-boot-logo.png">
    <img class="logo" src="/static/images/logo.png">
    <router-view/>
  </div>
</template>

<script>
import NavBar from './components/NavBar.vue'

export default {
  name: 'App',
  components: {
    'nav-bar': NavBar
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
  margin-top: 60px;
}
.logo {
  width: 12vw;
  margin-right: 1vw;
}
</style>

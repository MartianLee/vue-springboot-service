<template>
  <div id="app">
    <img class="logo" src="/static/images/spring-boot-logo.png">
    <img class="logo" src="/static/images/logo.png">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
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
  width: 24vw;
  margin-right: 2vw;
}
</style>

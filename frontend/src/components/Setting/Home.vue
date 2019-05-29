<template>
  <div>
    <img class="logo" src="/static/images/spring-boot-logo.png">
    <img class="logo" src="/static/images/logo.png">
    <h4>{{ msg }}</h4>
  </div>
</template>

<script>

export default {
  name: 'SettingHome',
  data () {
    return {
      msg: 'Developed by MartianLee with SpringBoot + Vue.js'
    }
  },
  created () {
  },
  methods: {
  }
}
</script>

<style scoped>
</style>

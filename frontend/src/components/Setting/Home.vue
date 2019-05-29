<template>
  <div>
    <img class="logo" src="/static/images/spring-boot-logo.png">
    <img class="logo" src="/static/images/logo.png">
    <h4>{{ msg }}</h4>
    <user-info></user-info>
  </div>
</template>

<script>
import UserInfo from '../User/UserInfo.vue'

export default {
  name: 'SettingHome',
  data () {
    return {
      msg: 'Developed by MartianLee with SpringBoot + Vue.js'
    }
  },
  created () {
  },
  components: {
    'user-info': UserInfo
  },
  methods: {
  }
}
</script>

<style scoped>
</style>

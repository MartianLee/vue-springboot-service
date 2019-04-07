<template>
  <div class="UserInfo">
    <h2>User Info</h2>
    <h4>{{user.name}} : {{user.email}} </h4>
    <h4>joined date : {{user.createdDate}} </h4>
    <!-- <h3 v-for="(user, index) in users" :key="user.id">
      <router-link :to="{ name: 'DetailUser', params: { id: index }}">
        {{user.name}} : {{user.email}}
      </router-link>
    </h3> -->
  </div>
</template>

<script>
export default {
  name: 'UserInfo',
  data () {
    return {
      msg: 'Welcome to Your SpringBoot + Vue.js App',
      user: {}
    }
  },
  created () {
    let token = window.$cookies.get('FootballDiary')
    if (token) {
      this.$http.get('/api/users', {
        headers: {
          Authorization: 'Bearer ' + token // the token is a variable which holds the token
        }
      }).then((resp) => {
        console.log(resp.data)
        this.user = resp.data
      })
    }
  }
}
</script>

<style scoped>
</style>

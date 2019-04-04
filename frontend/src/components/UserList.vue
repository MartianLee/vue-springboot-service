<template>
  <div class="UserList">
    <h2>User List</h2>
    <h3 v-for="(user, index) in users" :key="user.id">
      <router-link :to="{ name: 'DetailUser', params: { id: index }}">
        {{user.name}} : {{user.email}}
      </router-link>
    </h3>
  </div>
</template>

<script>
export default {
  name: 'UserList',
  data () {
    return {
      msg: 'Welcome to Your SpringBoot + Vue.js App',
      users: []
    }
  },
  created () {
    let token = window.$cookies.get('FootballDiary')
    console.log(token)
    this.$http.get('/api/users', {
      headers: {
        Authorization: 'Bearer ' + token // the token is a variable which holds the token
      }
    }).then((resp) => {
      this.users = resp.data.response
    })
  }
}
</script>

<style scoped>
</style>

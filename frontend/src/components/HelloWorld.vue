<template>
  <div class="hello">
    <h1>Match Log</h1>
    <h3>Archive and share your own diary of your best sports match!</h3>
    <h4>{{ msg }}</h4>
    <div v-show="loggedIn === false">
      <login></login>
      <button>
      <router-link :to="{ name: 'SignUp'}">
          SignUp
      </router-link>
    </button>
    </div>
    <div v-show="loggedIn === true">
      <userInfo></userInfo>
      <button v-on:click="onLogout">
        Logout
      </button>
      <router-link :to="{ name: 'UserInfo'}">
          UserInfo
      </router-link>
      <router-link :to="{ path: 'diary'}">
          TimeLine
      </router-link>
    </div>
  </div>
</template>

<script>
import Login from './Login.vue'
import UserInfo from './UserInfo.vue'

export default {
  name: 'HelloWorld',
  components: {
    'login': Login,
    'userInfo': UserInfo
  },
  data () {
    return {
      msg: 'Welcome to Your SpringBoot + Vue.js App',
      loggedIn: false,
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
        this.loggedIn = true
        this.user = resp.data
      }, (resp) => {
        window.$cookies.remove('FootballDiary')
        this.loggedIn = false
        console.log(this.loggedIn)
        console.log('error')
      })
    }
  },
  methods: {
    onLogout: function () {
      window.$cookies.remove('FootballDiary')
      this.loggedIn = false
      this.$router.push('/')
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1 {
  font-size: 3.5em;
}
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>

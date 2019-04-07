<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
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
      <router-link :to="{ name: 'TimeLine'}">
          TimeLine
      </router-link>
    </div>
    <h2>Essential Links</h2>
    <ul>
      <li>
        <a
          href="https://vuejs.org"
          target="_blank"
        >
          Core Docs
        </a>
      </li>
      <li>
        <a
          href="https://forum.vuejs.org"
          target="_blank"
        >
          Forum
        </a>
      </li>
      <li>
        <a
          href="https://chat.vuejs.org"
          target="_blank"
        >
          Community Chat
        </a>
      </li>
      <li>
        <a
          href="https://twitter.com/vuejs"
          target="_blank"
        >
          Twitter
        </a>
      </li>
      <br>
      <li>
        <a
          href="http://vuejs-templates.github.io/webpack/"
          target="_blank"
        >
          Docs for This Template
        </a>
      </li>
    </ul>
    <h2>Ecosystem</h2>
    <ul>
      <li>
        <a
          href="http://router.vuejs.org/"
          target="_blank"
        >
          vue-router
        </a>
      </li>
      <li>
        <a
          href="http://vuex.vuejs.org/"
          target="_blank"
        >
          vuex
        </a>
      </li>
      <li>
        <a
          href="http://vue-loader.vuejs.org/"
          target="_blank"
        >
          vue-loader
        </a>
      </li>
      <li>
        <a
          href="https://github.com/vuejs/awesome-vue"
          target="_blank"
        >
          awesome-vue
        </a>
      </li>
    </ul>
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

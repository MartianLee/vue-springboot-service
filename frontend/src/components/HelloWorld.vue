<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <div v-show="loggedIn === false">
      <login></login>
    </div>
    <div v-show="loggedIn === true">
      <userInfo></userInfo>
    </div>
    <button>
      <router-link :to="{ name: 'SignUp'}">
          SignUp
      </router-link>
    </button>
    <router-link :to="{ name: 'UserInfo'}">
          UserInfo
      </router-link>
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
    console.log(token)
    if (token) {
      this.$http.get('/api/users', {
        headers: {
          Authorization: 'Bearer ' + token // the token is a variable which holds the token
        }
      }).then((resp) => {
        console.log(resp.data)
        this.loggedInSucceed()
        this.user = resp.data
      }, (resp) => {
        window.$cookies.delete('FootballDiary')
        this.loggedInFailed()
        console.log(this.loggedIn)
        console.log('error')
      })
    }
  },
  methods: {
    loggedInSucceed: function () {
      this.loggedIn = true
    },
    loggedInFailed: function () {
      this.loggedIn = false
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

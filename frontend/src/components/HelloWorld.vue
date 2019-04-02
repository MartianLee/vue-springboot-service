<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <login></login>
    <button>
      <router-link :to="{ name: 'SignUp'}">
          SignUp
      </router-link>
    </button>
    <h2>User List</h2>
    <h3 v-for="(user, index) in users" :key="user.id">
      <router-link :to="{ name: 'DetailUser', params: { id: index }}">
        {{user.name}} : {{user.email}}
      </router-link>
    </h3>
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

export default {
  name: 'HelloWorld',
  components: {
    'login': Login
  },
  data () {
    return {
      msg: 'Welcome to Your SpringBoot + Vue.js App',
      users: []
    }
  },
  created () {
    this.$http.get('/api/users').then((resp) => {
      this.users = resp.data.response
      console.log('asdfasdf')
    })
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

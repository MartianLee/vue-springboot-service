<template>
  <div class="hello">
    <h1>Match Log</h1>
    <h3>Archive and share your own diary of your best sports match!</h3>
    <h4>{{ msg }}</h4>
    <div v-show="isloggedIn === false">
      <login></login>
    </div>
    <div v-show="isloggedIn === true">
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
import { mapGetters } from 'vuex'

export default {
  name: 'HelloWorld',
  components: {
    'login': Login,
    'userInfo': UserInfo
  },
  data () {
    return {
      msg: 'Developed by MartianLee with SpringBoot + Vue.js',
      user: {}
    }
  },
  created () {
    let token = window.$cookies.get('FootballDiary')
    this.$store.commit('setToken', token)
    this.$store.dispatch('getUserFromServer')
  },
  computed: {
    ...mapGetters([
      'isloggedIn'
    ])
  },
  methods: {
    onLogout: function () {
      window.$cookies.remove('FootballDiary')
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

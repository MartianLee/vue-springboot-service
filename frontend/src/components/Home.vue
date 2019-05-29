<template>
  <div class="home">
    <div class="header">
      <v-container bg fill-height grid-list-md text-xs-center>
        <v-layout row wrap align-center>
          <v-flex>
            <h1>Match Log</h1>
          </v-flex>
        </v-layout>
      </v-container>
    </div>
    <div class="sub-header">
      <v-container bg fill-height grid-list-md text-xs-center>
        <v-layout row wrap align-center>
          <v-flex>
            <h3>Archive and share your own log of your best sports match!</h3>
            <h3>내가 관람한 스포츠 경기 기록을 모으고 공유해 보세요!</h3>
          </v-flex>
        </v-layout>
      </v-container>
    </div>
    <div v-show="isloggedIn === false">
      <login></login>
    </div>
    <div v-show="isloggedIn === true">
      {{user.name}}님 안녕하세요!
      <v-btn v-on:click="onLogout">
        Logout
      </v-btn>
    </div>
    <h2>Upcoming Matches</h2>
    <h2>My Recent Logs</h2>
    <my-log-component></my-log-component>
    <h2>Log Timeline</h2>
    <log-timeline></log-timeline>
  </div>
</template>

<script>
import Login from './Login.vue'
import LogTimeline from './Diary/LogTimeline.vue'
import MyLogComponent from './Diary/MyLogComponent.vue'
import { mapGetters, mapState } from 'vuex'

export default {
  name: 'HelloWorld',
  components: {
    'login': Login,
    'logTimeline': LogTimeline,
    'my-log-component': MyLogComponent
  },
  data () {
    return {
    }
  },
  created () {
    let token = window.$cookies.get('FootballDiary')
    if (token) {
      this.$store.commit('setToken', token)
      this.$store.dispatch('getUserFromServer')
    } else {
      this.onLogout()
    }
  },
  computed: {
    ...mapGetters([
      'isloggedIn'
    ]),
    ...mapState([
      'user'
    ])
  },
  methods: {
    onLogout: function () {
      this.$store.commit('logout')
      window.$cookies.remove('FootballDiary')
      this.$router.push('/')
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.home {
  margin-bottom: 60px;
}
.header {
  height: 30vh;
}
.sub-header {
  text-align: left;
}
h1 {
  font-size: 4em;
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

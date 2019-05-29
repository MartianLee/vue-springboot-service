<template>
  <div class="TimeLine">
    <h2>Diaries</h2>
    <div class="text-right mb-2">
      <router-link :to="{ path: 'diary/write'}">
        <v-btn>
          Write
        </v-btn>
      </router-link>
    </div>
    <div class="container">
      <my-log-component></my-log-component>
    </div>
  </div>
</template>

<script>
import Buttons from './Buttons.vue'
import MyLogComponent from './MyLogComponent.vue'

export default {
  name: 'TimeLine',
  data () {
    return {
      user: {},
      diaries: []
    }
  },
  components: {
    'edit-buttons': Buttons,
    'my-log-component': MyLogComponent
  },
  created () {
    let token = window.$cookies.get('FootballDiary')
    if (token) {
      this.$http.get('/api/diary', {
        headers: {
          Authorization: 'Bearer ' + token // the token is a variable which holds the token
        }
      }).then((resp) => {
        console.log(resp.data)
        this.diaries = resp.data
      })
    }
  }
}
</script>

<style scoped>
.container {
  margin: 0 auto;
  text-align: left;
  padding: 0;
}
.log-title {
  color: white;
}
</style>

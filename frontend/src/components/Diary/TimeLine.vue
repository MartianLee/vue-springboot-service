<template>
  <div class="TimeLine">
    <h2>Diares</h2>
    <div class="text-right">
      <router-link :to="{ path: 'diary/write'}">
          Write
      </router-link>
    </div>
    <hr>
    <div v-for="(diary, index) in diaries" :key="diary.id">
      <h3>
        <router-link :to="{ path: `diary/${diary.id}`}">
          {{ diaries.length - index }}. {{ diary.title }}
        </router-link>
      </h3>
      <h4>
        <!-- {{ diary.homeTeam.title }} vs {{ diary.awayTeam.title }} -->
      </h4>
      <h5>
          {{ diary.modifiedDate }}
      </h5>
      <p>
        {{ diary.content }}
      <p>
      <hr>
    </div>
  </div>
</template>

<script>
export default {
  name: 'TimeLine',
  data () {
    return {
      msg: 'Welcome to Your SpringBoot + Vue.js App',
      user: {},
      diaries: []
    }
  },
  created () {
    let token = window.$cookies.get('FootballDiary')
    console.log(token)
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
</style>

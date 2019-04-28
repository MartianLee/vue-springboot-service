<template>
  <div class="DetailDiary">
    <h1>{{ diary.title }}</h1>
    <h2> {{ diary.match.homeTeam.name }}({{ diary.match.goalsHomeTeam }}) vs {{diary.match.awayTeam.name}}({{ diary.match.goalsAwayTeam }}) </h2>
    <h3>Written by {{ diary.users.name }} at {{diary.modifiedDate}} </h3>
    <router-link :to="{ path: 'modify' }" append>
        Modify
    </router-link>
    <hr>
    <p>
        {{diary.content}}
    </p>
  </div>
</template>

<script>
export default {
  name: 'DetailDiary',
  data () {
    return {
      diary: {
        users: {
          name: ''
        },
        match: {
          homeTeam: {},
          awayTeam: {}
        },
        modifiedDate: ''
      }
    }
  },
  created () {
    let token = window.$cookies.get('FootballDiary')
    let id = this.$route.params.id
    if (token) {
      this.$http.get(`/api/diary/${id}`, {
        headers: {
          Authorization: 'Bearer ' + token // the token is a variable which holds the token
        }
      }).then((resp) => {
        this.diary = resp.data.response
      })
    }
  }
}
</script>

<style scoped>
</style>

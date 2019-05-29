<template>
  <div class="TimeLine">
    <div v-for="(diary, index) in diaries" :key="diary.id">
    <v-card
    class="mx-auto mb-3"
    color=gray
    dark
    max-width="480"
    >
        <v-card-title>
        <v-icon
        large
        left
        >
        </v-icon>
        <span class="title font-weight-light">
            <router-link :to="{ path: `diary/${diary.id}`}" class="log-title">
            {{ diaries.length - index }}. {{ diary.title }}
            <br>
            {{ `${diary.match.homeTeam.name} vs ${diary.match.awayTeam.name}` }}
            </router-link>
        </span>
        </v-card-title>
        <div class="text-xs-right">
        <edit-buttons :id="diary.id"></edit-buttons>
        </div>
        <v-card-text class="headline font-weight-bold">
        <h6>
            {{ diary.modifiedDate }}
        </h6>
        <p>
            {{ diary.content }}
        </p>
        </v-card-text>
    </v-card>
    </div>
  </div>
</template>

<script>
import Buttons from './Buttons.vue'

export default {
  name: 'MyTimeLine',
  data () {
    return {
      user: {},
      diaries: []
    }
  },
  components: {
    'edit-buttons': Buttons
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
.log-title {
  color: white;
}
</style>

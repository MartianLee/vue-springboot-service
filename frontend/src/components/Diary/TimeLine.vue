<template>
  <div class="TimeLine">
    <h2>Diaries</h2>
    <div class="text-right">
      <button>
        <router-link :to="{ path: 'diary/write'}">
            Write
        </router-link>
      </button>
    </div>
    <hr>
    <div class="container">
      <div v-for="(diary, index) in diaries" :key="diary.id">
        <h3>
          <router-link :to="{ path: `diary/${diary.id}`}">
            {{ diaries.length - index }}. {{ diary.title }}
          </router-link>
        </h3>
        <edit-buttons :id="diary.id"></edit-buttons>
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
  </div>
</template>

<script>
import Buttons from './Buttons.vue'

export default {
  name: 'TimeLine',
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
.container {
  margin: 0 auto;
  width: 60%;
  text-align: left;
}
</style>

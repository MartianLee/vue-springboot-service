<template>
  <div class="TimeLine">
    <h2>Matches</h2>
    <div class="text-right">
      <button v-on:click="refreshMatch">
        refresh
      </button>
    </div>
    <hr>
    <div class="container">
      <div v-for="match in matches" :key="match.id">
        <h3>
          <router-link :to="{ path: `match/${match.id}`}">
            {{ match.homeTeam.name }} vs {{ match.awayTeam.name }} at {{ match.matchDate }}
          </router-link>
        </h3>
        <edit-buttons :id="match.id"></edit-buttons>
        <h4>
            {{ match.goalsHomeTeam }} : {{ match.goalsAwayTeam }}
            <!-- {{ match.homeTeam.name }} vs {{ match.awayTeam.name }} -->
        </h4>
        <h5>
            crawlled at {{ match.modifiedDate }}
        </h5>
        <hr>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MatchHome',
  data () {
    return {
      matches: []
    }
  },
  created () {
    let token = window.$cookies.get('FootballDiary')
    if (token) {
      this.$http.get('/api/match/', {
        headers: {
          Authorization: 'Bearer ' + token // the token is a variable which holds the token
        }
      }).then((resp) => {
        console.log(resp.data)
        this.matches = resp.data.response
      })
    }
  },
  methods: {
    refreshMatch: function () {
      let token = window.$cookies.get('FootballDiary')
      this.$http.get('/api/match/refresh', {
        headers: {
          Authorization: 'Bearer ' + token // the token is a variable which holds the token
        }
      }).then((resp) => {
        console.log(resp.data)
        this.$router.push('/match')
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

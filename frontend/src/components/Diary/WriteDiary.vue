<template>
  <div class="WriteDiary">
    <h1>Write Diary</h1>
    <div class="diary">
        <form class="diary-form" v-on:submit.prevent="onCreateDiary" method="POST">
            <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
            <div class="row">
                <div class="input-field col s12">
                  <label for="title">Title</label>
                  <input id="title" name="title" type="title" class="validate" v-model="title"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                  <label for="content">Content</label>
                  <textarea id="content" name="content" rows="6" class="validate" v-model="content"/>
                </div>
            </div>
            <div class="row">
              <select v-model="selected_league" v-on:change="onChangeLeague()">
                <option v-for="league in league_list" v-bind:key="league.id" v-bind:value="league.id">
                  {{ league.name }}
                </option>
              </select>
              <span>선택함: {{ selected_league }}</span>
            </div>
            <div class="row">
              <select v-model="selected_team" v-on:change="onChangeTeam()">
                <option v-for="team in team_list" v-bind:key="team.id" v-bind:value="team.id">
                  {{ team.name }}
                </option>
              </select>
              <span>선택함: {{ selected_team }}</span>
            </div>
            <div class="row">
              <select v-model="selected_match">
                <option v-for="match in match_list" v-bind:key="match.id" v-bind:value="match.id">
                  {{ match.homeTeam.name }} vs {{ match.awayTeam.name }} at {{ match.matchDate }}
                </option>
              </select>
              <span>선택함: {{ selected_match }}</span>
            </div>
            <input class="diary-btn waves-effect waves-light btn" type="submit" value="글쓰기" />
        </form>
    </div>
  </div>
</template>

<script>

export default {
  name: 'WriteDiary',
  data () {
    return {
      title: '',
      content: '',
      league_list: [],
      team_list: [],
      match_list: [],
      selected_league: '',
      selected_team: '',
      selected_match: ''
    }
  },
  created () {
    let token = window.$cookies.get('FootballDiary')
    if (token) {
      this.$http.get('/api/league/',
        {
          headers: {
            Authorization: 'Bearer ' + token, // the token is a variable which holds the token
            'Content-Type': 'application/json'
          }
        }, () => {
          console.log('failed')
        }).then(resp => {
        this.league_list = resp.data.response
      })
    }
  },
  methods: {
    onChangeLeague: function () {
      let token = window.$cookies.get('FootballDiary')
      if (token) {
        this.$http.get('/api/team/?league=' + this.selected_league,
          {
            headers: {
              Authorization: 'Bearer ' + token, // the token is a variable which holds the token
              'Content-Type': 'application/json'
            }
          }, () => {
            console.log('failed')
          }).then(resp => {
          this.team_list = resp.data.response
        })
      }
    },
    onChangeTeam: function () {
      let token = window.$cookies.get('FootballDiary')
      if (token) {
        this.$http.get('/api/match/?team=' + this.selected_team,
          {
            headers: {
              Authorization: 'Bearer ' + token, // the token is a variable which holds the token
              'Content-Type': 'application/json'
            }
          }, () => {
            console.log('failed')
          }).then(resp => {
          this.match_list = resp.data.response
        })
      }
    },
    onCreateDiary: function () {
      if (!this.selected_match) {
        alert('Please select Match!')
        return
      }
      let token = window.$cookies.get('FootballDiary')
      if (token) {
        this.$http.post('/api/diary', {
          title: this.title,
          content: this.content,
          matchId: this.selected_match
        },
        {
          headers: {
            Authorization: 'Bearer ' + token, // the token is a variable which holds the token
            'Content-Type': 'application/json'
          }
        }, () => {
          console.log('failed')
        }).then(res => {
          this.$router.push('/diary')
        })
      }
    }
  }
}
</script>

<style scoped>
</style>

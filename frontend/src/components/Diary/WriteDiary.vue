<template>
  <div class="WriteDiary">
    <h1>Write Diary</h1>
    <div class="container">
      <v-btn v-on:click="onClickBack">
        back
      </v-btn>
      <div class="diary">
        <form class="diary-form" v-on:submit.prevent="onCreateDiary" method="POST">
            <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
            <v-text-field
              v-model="title"
              :counter="100"
              label="Title"
              required
            ></v-text-field>
            <v-textarea
              v-model="content"
              :counter="1000"
              label="Content"
              required
            ></v-textarea>
            <v-layout row wrap align-center>
              <v-flex sm6 xs12>
                <v-subheader>League</v-subheader>
              </v-flex>
              <v-flex sm6 xs12>
                <v-select :items="league_list"
                  v-on:change="onChangeLeague()"
                  v-model="selected_league"
                  item-text="name"
                  item-value="id"
                  outline>
                </v-select>
              </v-flex>
              <v-flex sm6 xs12>
                <v-subheader>Team</v-subheader>
              </v-flex>
              <v-flex sm6 xs12>
                <v-select :items="team_list"
                  v-on:change="onChangeTeam()"
                  v-model="selected_team"
                  item-text="name"
                  item-value="id"
                  outline>
                </v-select>
              </v-flex>
              <v-flex sm6 xs12>
                <v-subheader>Match</v-subheader>
              </v-flex>
              <v-flex sm6 xs12>
                <v-select :items="match_list"
                :item-text="matchTitle"
                v-model="selected_match"
                item-value="id"
                outline>
                </v-select>
              </v-flex>
            </v-layout>
            <v-btn type="submit">write</v-btn>
        </form>
      </div>
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
      }).then(() => {
        console.log(this.$route.params)
        if (this.$route.params.id) {
          this.$http.get('/api/match/' + this.$route.params.id,
            {
              headers: {
                Authorization: 'Bearer ' + token, // the token is a variable which holds the token
                'Content-Type': 'application/json'
              }
            }, () => {
              console.log('failed')
            }).then(resp => {
            console.log('asdf')
            this.selected_match = resp.data.response.id
            this.selected_league = resp.data.response.league.id
            this.selected_team = resp.data.response.homeTeam.id
            this.matchId = resp.data.response.id
            this.onChangeLeague()
            this.onChangeTeam()
          })
        }
      })
    }
  },
  methods: {
    matchTitle: function (item) {
      if (item) {
        return `${item.homeTeam.name} vs ${item.awayTeam.name}`
      }
    },
    onClickBack: function () {
      this.$router.go(-1)
    },
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
          this.$store.dispatch('getLogById')
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

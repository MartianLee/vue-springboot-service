<template>
  <div class="TimeLine">
    <h2>Matches</h2>
    <div class="text-right">
      <v-btn :loading="loading"
        :disabled="loading"
        color="secondary"
        v-on:click="refreshMatch(2)">
        refresh EPL Matches
      </v-btn>
      <v-btn :loading="loading"
        :disabled="loading"
        color="secondary"
        v-on:click="refreshMatch(87)">
        refresh La Liga Matches
      </v-btn>
    </div>
    <div>
        <v-layout wrap>
        <v-flex
          xs12
          class="mb-3"
        >
          <v-sheet>
            <v-calendar
              ref="calendar"
              v-model="start"
              :type="type"
              :end="end"
              color="primary"
            >
              <template v-slot:day="{ date }">
                <template v-for="event in eventsMap[date]">
                  <v-menu
                    :key="event.title"
                    v-model="event.open"
                    full-width
                    offset-x
                  >
                    <template v-slot:activator="{ on }">
                      <div
                        v-if="!event.time"
                        v-ripple
                        class="my-event"
                        v-on="on"
                        v-html="event.title"
                      ></div>
                    </template>
                    <v-card
                      color="grey lighten-4"
                      min-width="350px"
                      flat
                    >
                      <v-toolbar
                        color="primary"
                        dark
                      >
                        <v-btn icon>
                          <v-icon>edit</v-icon>
                        </v-btn>
                        <v-toolbar-title v-html="event.title"></v-toolbar-title>
                        <v-spacer></v-spacer>
                        <v-btn icon>
                          <v-icon>favorite</v-icon>
                        </v-btn>
                        <v-btn icon>
                          <v-icon>more_vert</v-icon>
                        </v-btn>
                      </v-toolbar>
                      <v-card-title primary-title>
                        <span v-html="event.details"></span>
                      </v-card-title>
                      <v-card-actions>
                        <v-btn
                          flat
                          color="secondary"
                        >
                          Cancel
                        </v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-menu>
                </template>
              </template>
            </v-calendar>
          </v-sheet>
        </v-flex>

        <v-flex
          sm4
          xs12
          class="text-sm-left text-xs-center"
        >
          <v-btn @click="$refs.calendar.prev()">
            <v-icon
              dark
              left
            >
              keyboard_arrow_left
            </v-icon>
            Prev
          </v-btn>
        </v-flex>
        <v-flex
          sm4
          xs12
          class="text-xs-center"
        >
          <v-select
            v-model="type"
            :items="typeOptions"
            label="Type"
          ></v-select>
        </v-flex>
        <v-flex
          sm4
          xs12
          class="text-sm-right text-xs-center"
        >
          <v-btn @click="$refs.calendar.next()">
            Next
            <v-icon
              right
              dark
            >
              keyboard_arrow_right
            </v-icon>
          </v-btn>
        </v-flex>
      </v-layout>
    </div>
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
import Buttons from './Buttons.vue'

export default {
  name: 'MatchHome',
  data () {
    return {
      matches: [],
      loading: false,
      type: 'month',
      start: '2019-05-01',
      end: '2019-05-31',
      typeOptions: [
        { text: '4 Day', value: '4day' },
        { text: 'Week', value: 'week' },
        { text: 'Month', value: 'month' }
      ],
      events: [
        {
          title: 'Vacation',
          details: 'Going to the beach!',
          date: '2018-03-30',
          open: false
        },
        {
          title: 'Vacation',
          details: 'Going to the beach!',
          date: '2018-03-31',
          open: false
        },
        {
          title: 'Vacation',
          details: 'Going to the beach!',
          date: '2019-04-01',
          open: false
        },
        {
          title: 'Meeting',
          details: 'Spending time on how we do not have enough time',
          date: '2019-04-07',
          open: false
        },
        {
          title: '30th Birthday',
          details: 'Celebrate responsibly',
          date: '2019-04-03',
          open: false
        },
        {
          title: 'New Year',
          details: 'Eat chocolate until you pass out',
          date: '2019-04-29',
          open: false
        },
        {
          title: 'Conference',
          details: 'Mute myself the whole time and wonder why I am on this call',
          date: '2019-05-03',
          open: false
        },
        {
          title: 'Hackathon',
          details: 'Code like there is no tommorrow',
          date: '2019-05-19',
          open: false
        }
      ]
    }
  },
  components: {
    'edit-buttons': Buttons
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
        this.matches.map(match => {
          match.title = `${match.homeTeam.name} vs ${match.awayTeam.name}`
          match.details = `${match.goalsHomeTeam} : ${match.goalsAwayTeam}`
          match.open = false
          match.date = match.matchDate.substring(0, 10)
        })
      })
    }
  },
  computed: {
    // convert the list of events into a map of lists keyed by date
    eventsMap () {
      const map = {}
      this.matches.forEach(e => (map[e.date] = map[e.date] || []).push(e))
      return map
    }
  },
  methods: {
    refreshMatch: function (leagueId) {
      let token = window.$cookies.get('FootballDiary')
      this.$http.get(`/api/match/refresh/${leagueId}`, {
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
  text-align: left;
}
.my-event {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  border-radius: 2px;
  background-color: #1867c0;
  color: #ffffff;
  border: 1px solid #1867c0;
  width: 100%;
  font-size: 12px;
  padding: 3px;
  cursor: pointer;
  margin-bottom: 1px;
}

</style>

<template>
  <div class="TimeLine">
    <div v-for="diary in log" :key="diary.id">
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
        message
        </v-icon>
        <span class="title font-weight-medium">
            <router-link :to="{ path: `diary/${diary.id}`}" class="log-title">
            {{ diary.title }}
            <br>
            {{ `${diary.match.homeTeam.name} vs ${diary.match.awayTeam.name}` }}
            </router-link>
        </span>
        </v-card-title>
        <div class="text-xs-right">
          <span class="date">
            {{ changeDateFormat(diary.modifiedDate) }}
          </span>
          <edit-buttons :id="diary.id"></edit-buttons>
        </div>
        <v-card-text class="headline font-weight-light">
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
import { mapState } from 'vuex'

export default {
  name: 'MyTimeLine',
  data () {
    return {
      diaries: []
    }
  },
  components: {
    'edit-buttons': Buttons
  },
  created () {
    console.log('MyLogConponent.vue')
  },
  computed: {
    ...mapState([
      'user',
      'log'
    ])
  },
  watch: {
    user: function (newQuestion) {
      console.log('user Changed')
      this.$store.dispatch('getLogById')
    }
  },
  methods: {
    changeDateFormat: function (matchDate) {
      let d = new Date(matchDate)
      let date = d.getDate()
      let month = d.getMonth() + 1
      let year = d.getFullYear()
      if (month < 10) month = '0' + month
      if (date < 10) date = '0' + date
      return `${year}-${month}-${date}`
    }
  }
}
</script>

<style scoped>
.log-title {
  color: white;
  text-decoration: none;
}
.date {
  font-size: 0.8rem;
  margin-right: 10px;
}
</style>

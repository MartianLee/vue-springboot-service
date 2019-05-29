<template>
  <v-toolbar fixed>
    <v-toolbar-side-icon></v-toolbar-side-icon>
    <v-toolbar-title>MatchLog</v-toolbar-title>
    <v-spacer></v-spacer>
    <v-toolbar-items class="hidden-xs-and-down">
    <v-btn flat>
        <v-avatar
        :size=36
        color="grey lighten-4"
        >
        <img src="https://vuetifyjs.com/apple-touch-icon-180x180.png" alt="avatar">
        </v-avatar>
    </v-btn>
    </v-toolbar-items>
  </v-toolbar>
</template>

<script>
export default {
  name: 'NavBarTop',
  data () {
    return {
      tile: ''
    }
  },
  created () {
  },
  methods: {
    onNavHome: function () {
      this.$router.push('/')
    },
    onNavCalendar: function () {
      this.$router.push('/match')
    }
  }
}
</script>

<style scoped>
</style>

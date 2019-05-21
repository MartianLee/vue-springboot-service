<template>
  <v-toolbar fixed>
    <v-toolbar-side-icon></v-toolbar-side-icon>
    <v-toolbar-title>MatchLog</v-toolbar-title>
    <v-spacer></v-spacer>
    <v-toolbar-items class="hidden-xs-and-down">
    <v-btn flat>Link1</v-btn>
    <v-btn flat>Link2</v-btn>
    </v-toolbar-items>
  </v-toolbar>
</template>

<script>
export default {
  name: 'NavBarTop',
  data () {
    return {
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

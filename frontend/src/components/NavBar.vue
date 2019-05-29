<template>
  <v-bottom-nav
    :active.sync="bottomNav"
    :value="true"
    color="white"
    fixed
    >
    <v-btn
        color="teal"
        flat
        value="home"
        v-on:click="onNavHome"
    >
        <span>Home</span>
        <v-icon>home</v-icon>
    </v-btn>

    <v-btn
        color="teal"
        flat
        value="calendar"
        v-on:click="onNavCalendar"
    >
        <span>Calendar</span>
        <v-icon>event</v-icon>
    </v-btn>

    <v-btn
        color="teal"
        flat
        value="log"
        v-on:click="onNavLog"
    >
        <span>Log</span>
        <v-icon>inbox</v-icon>
    </v-btn>
    <v-btn
        color="teal"
        flat
        value="setting"
        v-on:click="onNavSetting"
    >
        <span>Setting</span>
        <v-icon>settings</v-icon>
    </v-btn>
    </v-bottom-nav>
</template>

<script>
export default {
  name: 'NavBar',
  data () {
    return {
      bottomNav: 'home'
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
    },
    onNavLog: function () {
      this.$router.push('/diary')
    },
    onNavSetting: function () {
      this.$router.push('/setting')
    }
  }
}
</script>

<style scoped>
</style>

<template>
  <v-bottom-nav
    :active.sync="bottomNav"
    :value="true"
    color="transparent"
    fixed
    >
    <v-btn
        color="teal"
        flat
        value="recent"
    >
        <span>Home</span>
        <v-icon>home</v-icon>
    </v-btn>

    <v-btn
        color="teal"
        flat
        value="favorites"
    >
        <span>Calendar</span>
        <v-icon>calendar</v-icon>
    </v-btn>

    <v-btn
        color="teal"
        flat
        value="nearby"
    >
        <span>Log</span>
        <v-icon>log</v-icon>
    </v-btn>
    <v-btn
        color="teal"
        flat
        value="setting"
    >
        <span>Setting</span>
        <v-icon>setting</v-icon>
    </v-btn>
    </v-bottom-nav>
</template>

<script>
export default {
  name: 'NavBar',
  data () {
    return {
      user: {}
    }
  },
  created () {
  }
}
</script>

<style scoped>
</style>

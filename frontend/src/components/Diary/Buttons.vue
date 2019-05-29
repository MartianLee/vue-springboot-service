<template>
  <div class="ModifyDiary">
    <v-btn v-on:click="onModify"> modify </v-btn>
    <v-btn v-on:click="onDelete"> delete </v-btn>
  </div>
</template>

<script>

export default {
  name: 'DiaryButtons',
  props: ['id'],
  data () {
    return {
      diary: {
        title: '',
        content: ''
      }
    }
  },
  methods: {
    onModify: function () {
      this.$router.push(`/diary/${this.id}/modify`)
    },
    onDelete: function () {
      let token = window.$cookies.get('FootballDiary')
      if (token) {
        this.$http.delete(`/api/diary/${this.id}`,
          {
            headers: {
              Authorization: 'Bearer ' + token, // the token is a variable which holds the token
              'Content-Type': 'application/json'
            }
          }, () => {
            console.log('failed')
          }).then(res => {
          location.reload()
          this.$router.push('/diary')
        })
      }
    }
  }
}
</script>

<style scoped>
button {
  font-size: 0.8rem;
  height: 100%;
  padding: 5px;
}
</style>

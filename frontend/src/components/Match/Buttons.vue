<template>
  <div class="ModifyDiary">
    <button v-on:click="onModify"> modify </button>
    <button v-on:click="onDelete"> delete </button>
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
      this.$router.push(`/match/${this.id}/modify`)
    },
    onDelete: function () {
      let token = window.$cookies.get('FootballDiary')
      if (token) {
        this.$http.delete(`/api/match/${this.id}`,
          {
            headers: {
              Authorization: 'Bearer ' + token, // the token is a variable which holds the token
              'Content-Type': 'application/json'
            }
          }, () => {
            console.log('failed')
          }).then(res => {
          location.reload()
          this.$router.push('/match')
        })
      }
    }
  }
}
</script>

<style scoped>
</style>

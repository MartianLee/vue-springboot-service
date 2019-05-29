<template>
  <div class="User">
    <h1>{{ user.name }} : {{user.phone}} </h1>
    point : {{ user.point }}
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: 'DetailUser',
  data () {
    return {
    }
  },
  created () {
    var id = this.$route.params.id
    this.$http.get(`/api/users/${id}`).then((resp) => {
      this.user = resp.data.response
    })
  },
  computed: {
    ...mapState([
      'user'
    ])
  },
}
</script>

<style scoped>
</style>

<template>
  <div class="WriteDiary">
    <h1>Write Diary</h1>
    <div class="diary">
        <form class="diary-form" v-on:submit.prevent="onCreateDiary" method="POST">
            <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
            <div class="row">
                <div class="input-field col s12">
                  <label for="title">Title</label>
                  <input id="title" name="title" type="title" class="validate" v-model="title"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                  <label for="content">Content</label>
                  <textarea id="content" name="content" rows="6" class="validate" v-model="content"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                  <label for="match_id">Match_id</label>
                  <input id="match_id" name="match_id" class="validate" v-model="match_id"/>
                </div>
            </div>
            <input class="diary-btn waves-effect waves-light btn" type="submit" value="글쓰기" />
        </form>
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
      match_id: ''
    }
  },
  created () {
  },
  methods: {
    onCreateDiary: function () {
      let token = window.$cookies.get('FootballDiary')
      if (token) {
        this.$http.post('/api/diary', {
          title: this.title,
          content: this.content,
          matchId: this.match_id
        },
        {
          headers: {
            Authorization: 'Bearer ' + token, // the token is a variable which holds the token
            'Content-Type': 'application/json'
          }
        }, () => {
          console.log('failed')
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

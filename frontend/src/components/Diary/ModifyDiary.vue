<template>
  <div class="ModifyDiary">
    <h1>Modify Diary</h1>
    <div class="diary">
        <v-form class="diary-form" v-on:submit.prevent="onCreateDiary" method="PUT">
            <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
            <div class="row">
                <div class="input-field col s12">
                    <label for="title">Title</label>
                <input id="title" name="title" type="title" class="validate" v-model="diary.title"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                <label for="content">Content</label>
                <textarea id="content" name="uid" rows="6" class="validate" v-model="diary.content"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                  <label for="match_id">Match_id</label>
                  <input id="match_id" name="match_id" class="validate" v-model="match_id"/>
                </div>
            </div>
            <input class="diary-btn waves-effect waves-light btn" type="submit" value="글쓰기" />
        </v-form>
    </div>
  </div>
</template>

<script>

export default {
  name: 'ModifyDiary',
  data () {
    return {
      diary: {
        title: '',
        content: ''
      }
    }
  },
  created () {
    let token = window.$cookies.get('FootballDiary')
    let id = this.$route.params.id
    if (token) {
      this.$http.get(`/api/diary/${id}`, {
        headers: {
          Authorization: 'Bearer ' + token // the token is a variable which holds the token
        }
      }).then((resp) => {
        this.diary = resp.data.response
      })
    }
  },
  methods: {
    onCreateDiary: function () {
      let token = window.$cookies.get('FootballDiary')
      let id = this.$route.params.id
      if (token) {
        this.$http.put(`/api/diary/${id}`, this.diary,
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

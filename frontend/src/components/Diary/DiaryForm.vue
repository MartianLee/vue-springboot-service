<template>
  <div class="SignUp">
    <h1>Write Diary</h1>
    <div class="Login">
        <form class="signup-form" v-on:submit.prevent="onSubmit" method="POST">
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
            <v-btn>
              <input class="signup-btn waves-effect waves-light btn" type="submit" value="글쓰기" />
            </v-btn>
        </form>
    </div>
  </div>
</template>

<script>

export default {
  name: 'DiaryForm',
  data () {
    return {
      diary: {
        title: '',
        content: ''
      }
    }
  },
  created () {
  },
  methods: {
    onSubmit: function () {
      console.log(diary);
    }
  }
}
</script>

<style scoped>
</style>

<template>
  <div class="SignUp">
    <h1>SignUp</h1>
    <div class="Login">
        <form class="signup-form" v-on:submit.prevent="onSignUpSubmit" method="POST">
            <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
            <div class="row">
                <div class="input-field col s12">
                <label for="email">Email</label>
                <input id="email" name="uemail" type="email" class="validate" v-model="email"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                <label for="user_name">Username</label>
                <input id="user_name" name="uid" type="text" class="validate" v-model="name"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                <label for="password">Password</label>
                <input id="password" name="upw" type="password" class="validate" v-model="password"/>
                </div>
            </div>
            <input class="signup-btn waves-effect waves-light btn" type="submit" value="가입하기" />
        </form>
    </div>
  </div>
</template>

<script>

export default {
  name: 'SignUp',
  data () {
    return {
      email: '',
      name: '',
      password: ''
    }
  },
  created () {
  },
  methods: {
    onSignUpSubmit: function () {
      console.log(window.$router)
      console.log(this.$router)
      this.$http.post('/api/users/create', {
        email: this.email,
        name: this.name,
        password: this.password
      }, () => {
        console.log('failed')
      }).then(res => {
        this.$router.push('/')
      })
    }
  }
}
</script>

<style scoped>
</style>

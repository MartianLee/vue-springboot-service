<template>
  <div class="Login">
    <h2>Login</h2>
    <div class="Login">
        <form class="login-form" v-on:submit.prevent="onLoginSubmit" method="POST">
            <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
            <div class="row">
                <div class="input-field col s12">
                  <label for="email">Email</label>
                  <input id="email" name="uemail" type="email" class="validate" v-model="email" />
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                  <label for="password">Password</label>
                  <input id="password" name="upw" type="password" class="validate" v-model="password" />
                </div>
            </div>
            <input class="login-btn waves-effect waves-light btn" type="submit" value="Login" />
        </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Login',
  data () {
    return {
      token: '',
      email: '',
      password: ''
    }
  },
  created () {
  },
  methods: {
    onLoginSubmit: function () {
      axios.post('/api/users/login', {
        email: this.email,
        password: this.password
      }).then(res => {
        window.$cookies.set('FootballDiary', res.data, '2d')
        console.log(res.data)
        location.reload()
      }, () => {
        console.log('failed')
      })
    }
  }
}
</script>

<style scoped>
</style>

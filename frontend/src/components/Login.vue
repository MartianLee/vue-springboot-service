<template>
  <div class="login">
    <h2>Login</h2>
    <div class="form-wrapper">
        <form class="login-form" v-on:submit.prevent="onLoginSubmit" method="POST">
            <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
            <v-text-field
              v-model="email"
              :counter="100"
              label="Email"
              required
            ></v-text-field>
            <v-text-field
              v-model="password"
              :counter="20"
              :rules="[rules.required, rules.min]"
              :type="password"
              label="Password"
              required
            ></v-text-field>
            <v-btn type="submit">Login</v-btn>
        </form>
        <router-link :to="{ name: 'SignUp'}">
          <v-btn>
            SignUp
          </v-btn>
        </router-link>
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
      password: '',
      rules: {
        required: value => !!value || 'Required.',
        min: v => v.length >= 4 || 'Min 4 characters',
        emailMatch: () => ('The email and password you entered don\'t match')
      }
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
        location.reload()
      }, () => {
        console.log('failed')
      })
    }
  }
}
</script>

<style scoped>
.form-wrapper {
  margin: 0 auto;
  width: 60%;
  text-align: left;
}
input, button {
  width: 100%;
}
label {
  width: 100%;
}
</style>

<template>
  <div class="SignUp">
    <h1>SignUp</h1>
    <div class="form-wrapper">
        <form class="signup-form" v-on:submit.prevent="onSignUpSubmit" method="POST">
            <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
            <v-text-field
              v-model="email"
              :counter="100"
              label="Email"
              required
            ></v-text-field>
            <v-text-field
              v-model="name"
              :counter="20"
              label="Username"
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
            <v-btn type="submit">Sign Up</v-btn>
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
    onSignUpSubmit: function () {
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
.form-wrapper {
  margin: 0 auto;
  width: 60%;
  text-align: left;
}
.signup-btn {
  margin-top: 20px;
}
input, button {
  width: 100%;
}
label {
  width: 100%;
}
</style>

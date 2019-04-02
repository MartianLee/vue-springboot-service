<template>
  <div class="Login">
    <h2>Login</h2>
    <div class="Login">
        <form class="login-form" action="/api/users/login" method="POST">
            <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
            <div class="row">
                <div class="input-field col s12">
                <input id="email" name="uemail" type="email" class="validate"/>
                <label for="email">Email</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                <input id="password" name="upw" type="password" class="validate"/>
                <label for="password">Password</label>
                </div>
            </div>
            <input class="login-btn waves-effect waves-light btn" type="submit" value="Login" />
        </form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
    }
  },
  created () {
  }
}
</script>

<style scoped>
</style>

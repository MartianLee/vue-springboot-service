<template>
  <div class="SignUp">
    <h1>SignUp</h1>
    <div class="Login">
        <form class="signup-form" action="/api/users/create" method="POST">
            <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
            <div class="row">
                <div class="input-field col s12">
                <input id="user_name" name="uid" type="text" class="validate"/>
                <label for="user_name">Username</label>
                </div>
            </div>
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
    }
  },
  created () {
  }
}
</script>

<style scoped>
</style>

<template>
  <div class="NavBar">
    <ul>
        <li>
            <router-link :to="{ path: '/'}">
                Home
            </router-link>
        </li>
        <li>
            <router-link :to="{ path: '/diary'}">
                Diaries
            </router-link>
        </li>
        <li>
            <router-link :to="{ path: '/diary'}">
                About
            </router-link>
        </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'NavBar',
  data () {
    return {
      user: {}
    }
  },
  created () {
  }
}
</script>

<style scoped>
    .NavBar{
        top: 0;
        position: fixed;
        width: 100%;
        text-align: center;
    }
    ul {
        padding: 0;
    }
    li {
        display: inline-block;
        margin-right: 30px;
    }
    li > a {
        text-decoration: none;
        color: inherit;
    }
</style>

<template>
  <div class="Diary">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'DiaryHome',
  data () {
    return {
      diary: {}
    }
  },
  created () {
  }
}
</script>

<style scoped>
</style>

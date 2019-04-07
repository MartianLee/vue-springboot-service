import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import SignUp from '@/components/SignUp'
import Login from '@/components/Login'
import UserInfo from '@/components/UserInfo'
import TimeLine from '@/components/TimeLine'
import WriteDiary from '@/components/WriteDiary'

Vue.use(Router)

export default new Router({
  // mode: 'history',
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/signup',
      name: 'SignUp',
      component: SignUp
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/userInfo',
      name: 'UserInfo',
      component: UserInfo
    },
    {
      path: '/timeLine',
      name: 'TimeLine',
      component: TimeLine
    },
    {
      path: '/writeDiary',
      name: 'WriteDiary',
      component: WriteDiary
    }
  ]
})

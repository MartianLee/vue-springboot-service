import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import SignUp from '@/components/SignUp'
import Login from '@/components/Login'
import UserInfo from '@/components/UserInfo'
import TimeLine from '@/components/Diary/TimeLine'
import WriteDiary from '@/components/Diary/WriteDiary'
import DetailDiary from '@/components/Diary/DetailDiary'
import ModifyDiary from '@/components/Diary/ModifyDiary'
import DiaryHome from '@/components/Diary/DiaryHome'

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
      path: '/diary',
      component: DiaryHome,
      children: [
        {
          path: '',
          component: TimeLine
        },
        {
          path: 'write',
          component: WriteDiary
        },
        {
          path: ':id',
          component: DetailDiary
        },
        {
          path: ':id/modify',
          component: ModifyDiary
        }
      ]
    }
  ]
})

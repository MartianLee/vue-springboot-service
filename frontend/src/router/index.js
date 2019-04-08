import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import NavBar from '@/components/NavBar'
import SignUp from '@/components/SignUp'
import Login from '@/components/Login'
import UserInfo from '@/components/UserInfo'
import TimeLine from '@/components/Diary/TimeLine'
import WriteDiary from '@/components/Diary/WriteDiary'
import DetailDiary from '@/components/Diary/DetailDiary'
import ModifyDiary from '@/components/Diary/ModifyDiary'
import DiaryHome from '@/components/Diary/DiaryHome'
import editDiaryButtons from '@/components/Diary/Buttons'

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
      path: '/navbar',
      name: 'NavBar',
      component: NavBar
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
    },
    {
      path: 'editButtons',
      name: 'editButtons',
      component: editDiaryButtons
    }
  ]
})

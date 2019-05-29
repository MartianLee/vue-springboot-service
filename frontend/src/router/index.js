import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import NavBar from '@/components/NavBar'
import SignUp from '@/components/SignUp'
import Login from '@/components/Login'
import UserInfo from '@/components/User/UserInfo'
import TimeLine from '@/components/Diary/TimeLine'
import WriteDiary from '@/components/Diary/WriteDiary'
import DetailDiary from '@/components/Diary/DetailDiary'
import ModifyDiary from '@/components/Diary/ModifyDiary'
import DiaryHome from '@/components/Diary/DiaryHome'
import editDiaryButtons from '@/components/Diary/Buttons'
import MatchHome from '@/components/Match/Home'
import SettingHome from '@/components/Setting/Home'

Vue.use(Router)

export default new Router({
  // mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
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
          path: 'write/:id',
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
    },
    {
      path: '/match',
      component: MatchHome
    },
    {
      path: '/setting',
      component: SettingHome
    }
  ]
})

import { createRouter, createWebHistory } from 'vue-router'
import { h } from 'vue'
import HomeView from '../views/HomeView.vue'
import UserLogin from '@/components/user/UserLogin.vue'
import UserJoin from '@/components/user/UserJoin.vue'
import BoardList from '@/components/community/BoardList.vue'
import BoardCreate from '@/components/community/BoardCreate.vue'
import BoardDetail from '@/components/community/BoardDetail.vue'
import MainSamsung from '@/components/main/MainSamsung.vue'
import MainLotte from '@/components/main/MainLotte.vue'
import BoardModify from '@/components/community/BoardModify.vue'
import MyPage from '@/components/user/MyPage.vue'
import CommentList from '@/components/community/CommentList.vue'
import Rule from '@/components/main/Rule.vue'
import GiantsNews from '@/components/news/GiantsNews.vue'
import LionsNews from '@/components/news/LionsNews.vue'
import MainView from '@/views/MainView.vue'
import GiantsNewsDetail from '@/components/news/GiantsNewsDetail.vue'
import LionsNewsDetail from '@/components/news/LionsNewsDetail.vue'
import NumberBaseball from '@/components/game/NumberBaseball.vue'
import MainKia from '@/components/main/MainKia.vue'
import MainDoosan from '@/components/main/MainDoosan.vue'
import MainHanhwa from '@/components/main/MainHanhwa.vue'
import AdminPage from '@/components/admin/AdminPage.vue'
import KakaoJoin from '@/components/user/KakaoJoin.vue'

const DynamicComponentWrapper = {
  props: ['component'],
  render() {
    return h(this.component)
  }
}

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: { hideHeaderFooter: true }
  },
  {
    path: '/admin',
    name: 'admin',
    component: AdminPage
  },
  {
    path: '/:teamId/login',
    name: 'login',
    component: UserLogin
  },
  {
    path: '/:teamId/join',
    name: 'join',
    component: UserJoin
  },
  {
    path: '/:teamId/rule',
    name: 'rule',
    component: Rule
  },
  {
    path: '/:teamId/read/:id',
    name: 'myPage',
    component: MyPage
  },
  {
    path: '/:teamId/news',
    name: 'news',
    component: DynamicComponentWrapper,
    beforeEnter: (to, from, next) => {
      if (to.params.teamId === '1') {
        to.meta.component = GiantsNews
      } else if (to.params.teamId === '2') {
        to.meta.component = LionsNews
      } else {
        return next({ name: 'home' })
      }
      next()
    },
    props: route => ({ component: route.meta.component })
  },
  {
    path: '/:teamId/news/detail',
    name: 'newsDetail',
    component: DynamicComponentWrapper,
    beforeEnter: (to, from, next) => {
      if (to.params.teamId === '1') {
        to.meta.component = GiantsNewsDetail
      } else if (to.params.teamId === '2') {
        to.meta.component = LionsNewsDetail
      } else {
        return next({ name: 'home' })
      }
      next()
    },
    props: route => ({ component: route.meta.component })
  },
  {
    path: '/:teamId/community',
    name: 'community',
    component: BoardList
  },
  {
    path: '/:teamId/community/create',
    name: 'boardCreate',
    component: BoardCreate
  },
  {
    path: '/:teamId/community/:id',
    name: 'boardDetail',
    component: BoardDetail,
    children: [
      {
        path: '',
        name: 'commentList',
        component: CommentList
      }
    ]
  },
  {
    path: '/:teamId/community/modify/:id',
    name: 'boardModify',
    component: BoardModify
  },
  {
    path: '/:teamId',
    name: 'main',
    component: DynamicComponentWrapper,
    beforeEnter: (to, from, next) => {
      if (to.params.teamId === '1') {
        to.meta.component = MainLotte
      } else if (to.params.teamId === '2') {
        to.meta.component = MainSamsung
      } else if (to.params.teamId === '3') {
        to.meta.component = MainKia
      } else if (to.params.teamId === '4') {
        to.meta.component = MainDoosan
      } else if (to.params.teamId === '5') {
        to.meta.component = MainHanhwa
      } else {
        return next({ name: 'home' })
      }
      next()
    },
    props: route => ({ component: route.meta.component })
  },
  {
    path: '/main',
    name: 'mainView',
    component: MainView,
    meta: { hideHeader: true }
  },
  {
    path: '/:teamId/num-baseball',
    name: 'numberBaseball',
    component: NumberBaseball
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

// 글로벌 가드 설정은 필요 없음

export default router

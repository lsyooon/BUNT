import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import UserLogin from "@/components/user/UserLogin.vue";
import UserJoin from "@/components/user/UserJoin.vue";
import BoardList from "@/components/community/BoardList.vue";
import BoardCreate from "@/components/community/BoardCreate.vue";
import BoardDetail from "@/components/community/BoardDetail.vue";
import MainSamsung from "@/components/main/MainSamsung.vue";
import MainLotte from "@/components/main/MainLotte.vue";
import BoardModify from "@/components/community/BoardModify.vue";
import MyPage from '@/components/user/MyPage.vue'
import CommentList from "@/components/community/CommentList.vue";
import Rule from '@/components/main/Rule.vue'
import GiantsNews from '@/components/news/GiantsNews.vue'
import NewsDetail from '@/components/news/NewsDetail.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/login',
      name: 'login',
      component: UserLogin
    },
    {
      path: '/join',
      name: 'join',
      component: UserJoin
    },
    {
      path: '/rule',
      name: 'rule',
      component: Rule
    },
    {
      path: '/read/:id',
      name: 'myPage',
      component: MyPage
    },
    {
      path: '/:teamId/news',
      name: 'giantsNews',
      component: GiantsNews
    },
    {
      path: '/:teamId/news/detail',
      name: 'newsDetail',
      component: NewsDetail
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
        },
      ]
    },
    {
      path: '/:teamId/community/modify/:id',
      name: 'boardModify',
      component: BoardModify
    },
    {
      path: '/main/samsung/:teamId',
      name: 'teamSamsung',
      component: MainSamsung
    },
    {
      path: '/main/lotte/:teamId',
      name: 'teamLotte',
      component: MainLotte
    }
  ]
})

export default router

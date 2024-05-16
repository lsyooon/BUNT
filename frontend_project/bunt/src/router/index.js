import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import UserLogin from "@/components/user/UserLogin.vue";
import UserJoin from "@/components/user/UserJoin.vue";
import BoardList from "@/components/community/BoardList.vue";

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
      path: '/community',
      name: 'community',
      component: BoardList,
      // children: [{
      //
      // }]
    }
  ]
})

export default router

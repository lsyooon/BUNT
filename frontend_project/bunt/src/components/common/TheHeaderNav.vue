<template>
  <header class="header">
    <nav class="navbar navbar-expand-lg navbar-dark custom-bg-color">
      <div class="container-fluid d-flex justify-content-between align-items-center">
        <a class="navbar-brand" href="/">
          <img src="../../assets/image_logo/KBO_logo.png" alt="Logo" width="200px">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
          <ul class="navbar-nav">
            <li class="nav-item">
              <RouterLink :to="linkToNews" class="nav-link custom-nav-link custom-font">
                NEWS
              </RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink :to="linkToCommunity" class="nav-link custom-nav-link custom-font">
                COMMUNITY
              </RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink :to="linkToRule" class="nav-link custom-nav-link custom-font">
                RULES
              </RouterLink>
            </li>
          </ul>
        </div>
        <ul class="navbar-nav align-items-center">
          <li v-if="loginUserName" class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
               aria-expanded="false" style="font-size: 18px">
              {{ loginUserName }}님 환영합니다
            </a>
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
              <li><a class="dropdown-item" @click="goMyPage">
                마이페이지
              </a></li>
              <li><a class="dropdown-item" @click="logout">
                로그아웃
              </a></li>
              <li v-if="loginUserName === 'admin'"><a class="dropdown-item" @click="goAdminPage">
                회원관리
              </a></li>
            </ul>
          </li>
          <li v-else class="nav-item">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
               aria-expanded="false" style="font-size: 18px">
              로그인 후 이용해주세요
            </a>
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
              <li><a class="dropdown-item" @click="goUserLogin">
                로그인
              </a></li>
              <li><a class="dropdown-item" @click="goUserJoin">
                회원가입
              </a></li>
            </ul>
          </li>
        </ul>
      </div>
    </nav>
  </header>
</template>

<script setup>
import { ref, onMounted, computed, watchEffect } from 'vue'
import { useUserStore } from '@/stores/user.js'
import { useRoute, useRouter } from 'vue-router'

const store = useUserStore()
const router = useRouter()
const route = useRoute()

// 반응형 데이터 선언
const loginUserName = ref('')

// 컴포넌트 마운트 시 sessionStorage에서 loginUser 값 로드
const loadUserFromSession = () => {
  const token = sessionStorage.getItem('access-token')
  if (token) {
    const storedUser = token.split('.')
    if (storedUser.length > 1) {
      loginUserName.value = JSON.parse(atob(storedUser[1]))['id']
    }
  }
}

onMounted(() => {
  loadUserFromSession()
})

watchEffect(() => {
  loadUserFromSession()
})

// 로그아웃 함수
const logout = () => {
  store.logout()
  loginUserName.value = ''
  router.push({ name: 'home' }) // 로그아웃 후 메인 페이지로 이동
}

const goUserLogin = () => {
  router.push({ name: 'login' }) // 로그인 페이지로 이동
}

const goUserJoin = () => {
  router.push({ name: 'join' }) // 회원가입 페이지로 이동
}

const goMyPage = () => {
  if (loginUserName.value) {
    router.push(`/${route.params.teamId}/read/${loginUserName.value}`)
  } else {
    console.error('loginUserName이 설정되지 않았습니다.')
  }
}

const goAdminPage = () => {
  router.push('/admin')
}

const linkToNews = computed(() => {
  return `/${route.params.teamId}/news`
})

const linkToCommunity = computed(() => {
  return `/${route.params.teamId}/community`
})

const linkToRule = computed(() => {
  return `/${route.params.teamId}/rule`
})
</script>

<style scoped>
/* 모든 요소의 기본 여백과 패딩을 제거 */
@font-face {
  font-family: 'KBODiaGothic';
  src: url('@/assets/fonts/KBO Dia Gothic_bold.ttf') format('truetype');
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

/* body와 html 요소의 여백과 패딩 제거 */
html, body {
  margin: 0;
  padding: 0;
  height: 100%;
}

.header {
  width: 100%;
  height: 70px;
  background-color: black; /* 헤더 배경색 */
  position: fixed;
  top: 0;
  z-index: 1000; /* 다른 요소보다 위에 오도록 z-index 추가 */
}

.custom-bg-color {
  background-color: black !important;
}

.custom-nav-link {
  margin-left: 15px;
  margin-right: 15px;
  color: white !important;
  font-size: 24px; /* 글자 크기 조정 */
  font-weight: bold; /* 글자 굵게 */
}

.custom-font {
  font-family: 'KBODiaGothic', sans-serif !important;
}

.custom-nav-link:hover {
  color: #ffc107 !important; /* Hover 색상 변경 */
}

.navbar-nav .nav-item {
  text-align: center;
  font-size: 50px;
}

.navbar-nav .btn {
  padding: 5px 10px; /* 버튼 패딩 조정 */
  font-size: 14px; /* 글자 크기 조정 */
  background-color: black;
  border-color: black;
}

.navbar-nav .btn:hover {
  background-color: black;
  border-color: black;
}

.navbar-nav .btn-warning {
  background-color: #FFC107;
  border-color: #FFC107;
  color: #fff;
}

.navbar-nav .btn-warning:hover {
  background-color: #FFA000;
  border-color: #FFA000;
}

.navbar-nav .btn-outline-light {
  color: #fff;
  border-color: #fff;
}

.navbar-nav .btn-outline-light:hover {
  background-color: rgba(255, 255, 255, 0.2);
}

.dropdown-menu {
  background-color: white; /* 드롭다운 메뉴 배경색 */
  border: none; /* 드롭다운 메뉴 테두리 제거 */
  border-radius: 8px; /* 드롭다운 메뉴 테두리 반경 둥글게 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 드롭다운 메뉴 그림자 추가 */
}

.dropdown-item {
  color: black; /* 드롭다운 메뉴 항목 글자 색상 */
}

.dropdown-item:hover {
  background-color: black; /* 드롭다운 메뉴 항목 hover 배경색 */
  color: #ffc107; /* 드롭다운 메뉴 항목 hover 글자 색상 */
}

.dropdown-menu-end {
  right: 0 !important;
  left: auto !important;
}

/* 작은 화면에서의 드롭다운 메뉴 스타일 */
@media (max-width: 991.98px) {
  .dropdown-menu {
    border-radius: 0; /* 작은 화면에서는 모서리를 각지게 */
  }

  .navbar-collapse {
    background-color: white; /* 작은 화면에서는 배경색 흰색으로 */
  }

  .nav-item .nav-link {
    color: black !important; /* 작은 화면에서는 링크 색상 변경 */
  }
}

.nav-tabs .nav-link {
  color: black;
}

.nav-tabs .nav-link.active {
  color: #fff;
  background-color: black;
  border-color: black;
}
</style>

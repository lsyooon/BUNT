<script setup>
import { ref, onMounted, computed } from 'vue';
import { useUserStore } from "@/stores/user.js";
import { useRoute, useRouter } from 'vue-router';

const store = useUserStore();
const router = useRouter();

// 반응형 데이터 선언
const loginUser = ref(null);
const loginUserName = ref('');

// 컴포넌트 마운트 시 sessionStorage에서 loginUser 값 로드
onMounted(() => {
  const storedUser = sessionStorage.getItem('loginUser');
  if (storedUser) {
    loginUser.value = JSON.parse(storedUser);
    loginUserName.value = loginUser.value.id; // 사용자 이름 속성 사용
  }
});

// 로그아웃 함수
const logout = () => {
  store.logout();
  loginUser.value = null;
  loginUserName.value = '';
  router.push({ name: 'home' }); // 로그아웃 후 메인 페이지로 이동
};

const goUserLogin = () => {
  router.push({ name: 'login' }); // 로그인 페이지로 이동
};

const goUserJoin = () => {
  router.push({ name: 'join' }); // 회원가입 페이지로 이동
};

const goMyPage = () => {
  console.log(loginUserName.value)
  if (loginUserName.value) {
    router.push(`/read/${loginUserName.value}`);
  } else {
    console.error('loginUserName이 설정되지 않았습니다.');
  }
};

const route = useRoute();

const linkToNews = computed(() => {
  return `/${route.params.teamId}/news`;
});

const linkToCommunity = computed(() => {
  return `/${route.params.teamId}/community`;
});
</script>

<template>
  <header class="header">
    <nav class="navbar navbar-expand-lg navbar-dark custom-bg-color">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">
          <img src="../../assets/image_logo/KBO_logo.png" alt="Logo" width="80">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav me-auto">
            <li class="nav-item">
              <RouterLink :to="linkToNews" class="nav-link custom-nav-link">NEWS</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink :to="linkToCommunity" class="nav-link custom-nav-link">COMMUNITY</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink to="/rule" class="nav-link custom-nav-link">RULE</RouterLink>
            </li>
          </ul>
          <ul class="navbar-nav ms-auto">
            <li v-if="loginUser" class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
                 aria-expanded="false">
                {{ loginUserName }}님 환영합니다
              </a>
              <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item" @click="goMyPage">MyPage</a></li>
                <li><a class="dropdown-item" @click="logout">Logout</a></li>
              </ul>
            </li>
            <li v-else class="nav-item">
              <a @click="goUserLogin" class="btn btn-outline-light me-2">로그인</a>
              <button @click="goUserJoin" class="btn btn-outline-light me-2">회원가입</button>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </header>
</template>

<style scoped>
/* 모든 요소의 기본 여백과 패딩을 제거 */
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
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 75px; /* TheHeaderNav 높이 조정 */
  background-color: #24418E; /* 헤더 배경색 */
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000; /* 다른 요소보다 위에 오도록 z-index 추가 */
}

.custom-bg-color {
  background-color: #24418E !important;
}

.custom-nav-link {
  color: white !important;
  font-size: 16px; /* 글자 크기 조정 */
  font-weight: bold; /* 글자 굵게 */
}

.custom-nav-link:hover {
  color: #ffc107 !important; /* Hover 색상 변경 */
}

.navbar-nav.nav-fill {
  width: 50%; /* 링크 항목들을 중앙으로 몰기 위해 넓이 조정 */
  justify-content: center; /* 가운데 정렬 */
}

.navbar-nav .nav-item {
  flex: 1;
  text-align: center;
}

.navbar-nav.ms-auto {
  margin-left: auto;
}

.navbar-nav .btn {
  padding: 5px 10px; /* 버튼 패딩 조정 */
  font-size: 14px; /* 글자 크기 조정 */
  background-color: #24418E;
  border-color: #24418E;
}

.navbar-nav .btn:hover {
  background-color: #1A2E5A;
  border-color: #1A2E5A;
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
  color: #24418E; /* 드롭다운 메뉴 항목 글자 색상 */
}

.dropdown-item:hover {
  background-color: #1A2E5A; /* 드롭다운 메뉴 항목 hover 배경색 */
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
    color: #24418E !important; /* 작은 화면에서는 링크 색상 변경 */
  }
}

.nav-tabs .nav-link {
  color: #24418E;
}

.nav-tabs .nav-link.active {
  color: #fff;
  background-color: #24418E;
  border-color: #24418E;
}
</style>

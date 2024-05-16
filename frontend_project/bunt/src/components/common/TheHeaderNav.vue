<script setup>
import { ref, onMounted } from 'vue';
import { useUserStore } from "@/stores/user.js";
import { useRouter } from 'vue-router';

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
  router.push({ name: 'home' }); // 로그아웃 후 로그인 페이지로 이동
};

const goUserLogin = () => {
  router.push({ name: 'login' }); // 로그인 페이지로 이동
};

const goUserJoin = () => {
  router.push({ name: 'join' }); // 회원가입 페이지로 이동
};
</script>

<template>
  <header>
    <nav class="navbar navbar-expand-lg navbar-dark custom-bg-color">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">
          <img src="../../assets/image_logo/KBO_logo.png" alt="Logo" width="100">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <RouterLink to="" class="nav-link">NEWS</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink to="" class="nav-link">COMMUNITY</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink to="" class="nav-link">SHOP</RouterLink>
            </li>
          </ul>
          <ul class="navbar-nav ms-auto">
            <li v-if="loginUser" class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                {{ loginUserName }}님 환영합니다
              </a>
              <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
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
.custom-bg-color {
  background-color: #24418E !important;
}

.navbar-nav .nav-link {
  color: white !important;
}

.navbar-nav .nav-link:hover {
  color: #ffc107 !important; /* Hover 색상 변경 */
}

.navbar-nav .btn {
  padding: 10px 20px;
  font-size: 16px;
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
</style>

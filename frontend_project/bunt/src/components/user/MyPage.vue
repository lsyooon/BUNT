<script setup>
import {ref, onMounted} from 'vue';
import {useRouter} from 'vue-router';
import {useUserStore} from '@/stores/user.js';
import axios from 'axios';

// 반응형 데이터 선언
const userInfo = ref(null);
const store = useUserStore();
const router = useRouter();

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

// 컴포넌트 마운트 시 사용자 정보 로드
onMounted(async () => {
  loadUserFromSession()
  if (loginUserName) {
    const userId = loginUserName.value; // 사용자 id 추출
    try {
      await store.getUserById(userId);
        userInfo.value = store.loginUser;
    } catch (error) {
      console.error('사용자 정보를 불러오는 중 오류가 발생했습니다.', error);
      router.push({name: 'home'});
    }
  } else {
    console.error('loginUser가 sessionStorage에 없습니다.');
    router.push({name: 'home'});
  }
});

// 사용자 정보 수정 함수
const modifyUser = async () => {
  try {
    const response = await axios.put('http://localhost:8080/api/modify', userInfo.value);
    if (response.status === 200) {
      alert('사용자 정보가 성공적으로 수정되었습니다.');
    } else {
      alert('사용자 정보 수정에 실패했습니다.');
    }
  } catch (error) {
    console.error('사용자 정보 수정 중 오류가 발생했습니다.', error);
    alert('사용자 정보 수정 중 오류가 발생했습니다.');
  }
};

// 사용자 탈퇴 함수
const deleteUser = async () => {
  const confirmation = confirm('정말로 회원 탈퇴를 하시겠습니까?');
  if (confirmation) {
    try {
      const userId = userInfo.value.id;
      await store.deleteUserById(userId);
      alert('회원 탈퇴가 성공적으로 처리되었습니다.');
      store.logout();
      router.push({name: 'home'});
    } catch (error) {
      console.error('회원 탈퇴 중 오류가 발생했습니다.', error);
      alert('회원 탈퇴 중 오류가 발생했습니다.');
    }
  }
};

// 이전 페이지로 이동 함수
const goBack = () => {
  router.back();
};
</script>

<template>
  <div class="wrapper">
    <div class="container mt-5">
      <h2>My Page</h2>
      <button @click="goBack" class="btn btn-secondary mb-3">이전으로</button>
      <div v-if="userInfo" class="card mt-4">
        <div class="card-body">
          <h5 class="card-title">{{ userInfo.name }}님의 정보</h5>
          <form @submit.prevent="modifyUser">
            <div class="mb-3">
              <label for="password" class="form-label d-flex justify-content-start">비밀번호</label>
              <input type="password" class="form-control" id="password" v-model="userInfo.password">
            </div>
            <div class="mb-3">
              <label for="email" class="form-label d-flex justify-content-start">이메일</label>
              <input type="email" class="form-control" id="email" v-model="userInfo.email" readonly>
            </div>
            <div class="mb-3">
              <label for="name" class="form-label d-flex justify-content-start">이름</label>
              <input type="text" class="form-control" id="name" v-model="userInfo.name">
            </div>
            <div class="mb-3">
              <label for="nickname" class="form-label d-flex justify-content-start">닉네임</label>
              <input type="text" class="form-control" id="nickname" v-model="userInfo.nickname" readonly>
            </div>
            <div class="mb-3">
              <label for="age" class="form-label d-flex justify-content-start">나이</label>
              <input type="number" class="form-control" id="age" v-model="userInfo.age">
            </div>
            <div class="mb-3">
              <label for="phoneNumber" class="form-label d-flex justify-content-start">전화번호</label>
              <input type="text" class="form-control" id="phoneNumber" v-model="userInfo.phoneNumber" readonly>
            </div>
            <div class="mb-3">
              <label for="address" class="form-label d-flex justify-content-start">주소</label>
              <input type="text" class="form-control" id="address" v-model="userInfo.address" readonly>
            </div>
            <div class="mb-3">
              <label for="joinDate" class="form-label d-flex justify-content-start">가입 날짜</label>
              <input type="text" class="form-control" id="joinDate" v-model="userInfo.joinDate" disabled>
            </div>
            <div class="mb-3">
              <label for="modDate" class="form-label d-flex justify-content-start">수정 날짜</label>
              <input type="text" class="form-control" id="modDate" v-model="userInfo.modDate" disabled>
            </div>
            <div class="d-flex justify-content-between">
              <button type="submit" class="btn btn-primary">정보 수정</button>
              <button type="button" class="btn btn-danger" @click="deleteUser">회원 탈퇴</button>
            </div>
          </form>
        </div>
      </div>
      <div v-else class="alert alert-warning mt-4">
        사용자 정보를 불러오는 중입니다...
      </div>
    </div>
  </div>
</template>

<style scoped>
.wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f8f9fa; /* 배경색을 필요에 따라 설정 */
}

.container {
  width: 100%;
  max-width: 600px; /* 중앙에 위치하게 하기 위해 최대 너비 설정 */
  margin-bottom: 3%;
}

.card {
  margin-top: 20px;
}

.card-title {
  font-size: 1.5em;
}

.card-text {
  margin-bottom: 10px;
}

.form-label {
  text-align: left; /* 레이블 왼쪽 정렬 */
}
</style>

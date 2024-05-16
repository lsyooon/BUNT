<script setup>
import { ref } from 'vue';
import { useUserStore } from "@/stores/user.js";

const store = useUserStore()

const user = ref({
  id: '',
  password: ''
})

const login = function() {
  store.login(user.value)
}
</script>

<template>
  <div class="background-container">
    <div class="overlay"></div>
    <div class="container mt-5">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <div class="card custom-card">
            <div class="card-body">
              <h2 class="card-title text-center mb-4">Login</h2>
              <form>
                <div class="mb-3">
                  <label for="id" class="form-label">아이디</label>
                  <input type="text" name="id" v-model="user.id" class="form-control" placeholder="아이디를 입력하세요.">
                </div>
                <div class="mb-3">
                  <label for="password" class="form-label">비밀번호</label>
                  <input type="password" name="password" v-model="user.password" class="form-control" placeholder="비밀번호를 입력하세요.">
                </div>
                <div class="d-grid">
                  <button @click.prevent="login" class="btn btn-primary">로그인</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.background-container {
  position: relative; /* 자식 요소의 위치를 기준으로 하도록 설정 */
  background-image: url('@/assets/images/login_background2.jpg'); /* 배경 이미지 경로 */
  background-size: cover; /* 화면에 꽉 차도록 이미지 크기 조정 */
  background-position: center; /* 이미지를 화면 중앙에 위치시킵니다 */
  background-repeat: no-repeat; /* 이미지 반복 방지 */
  height: 100vh; /* 전체 화면 높이 설정 */
  display: flex;
  justify-content: center;
  align-items: center;
}

.overlay {
  position: absolute; /* 부모 요소를 기준으로 위치 설정 */
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5); /* 배경색 투명도 조절 */
  z-index: 1; /* 오버레이가 다른 요소들 위에 나타나도록 설정 */
}

.container {
  position: relative; /* 부모 요소를 기준으로 위치 설정 */
  max-width: 700px;
  z-index: 2; /* 오버레이 아래에 위치하도록 설정 */
}

.custom-card {
  background-color: darkseagreen !important;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.card-title {
  font-size: 24px;
  font-weight: bold;
  color: white; /* 텍스트 색상 변경 */
}

.form-label {
  font-weight: 500;
  color: white; /* 레이블 색상 변경 */
}

.form-control {
  background-color: rgba(255, 255, 255, 0.8); /* 입력 필드 배경색 변경 */
}

.btn {
  padding: 10px;
  font-size: 16px;
  background: darkgray;
}
</style>

<script setup>
import {ref, onMounted} from 'vue'
import {useUserStore} from "@/stores/user.js"
import {useRouter} from 'vue-router'

const store = useUserStore()
const router = useRouter()  // Vue Router 사용

// 반응형 데이터 선언
const loginUser = ref(null)
const loginUserName = ref('')

// 컴포넌트 마운트 시 sessionStorage에서 loginUser 값 로드
onMounted(() => {
  const storedUser = sessionStorage.getItem('loginUser')
  if (storedUser) {
    loginUser.value = JSON.parse(storedUser)
    loginUserName.value = loginUser.value.id // 사용자 이름 속성 사용
  }
})

// 로그아웃 함수
const logout = () => {
  store.logout()
  loginUser.value = null
  loginUserName.value = ''
  router.push({name: 'login'})  // 로그아웃 후 로그인 페이지로 이동
}

// 페이지 이동 함수
// const goMyPage = () => {
//   router.push({name: 'mypage'})
// }
//
// const goContactView = () => {
//   router.push({name: 'contact'})
// }

const GoUserLogin = () => {
  router.push({name: 'login'})  // 로그인 페이지로 이동
}

const GoUserJoin = () => {
  router.push({name: 'join'})  // 회원가입 페이지로 이동
}
</script>

<template>
  <div>
    <header>
      <nav>
        <div class="d-flex justify-content-between">
          <img src="../../assets/image_logo/KBO_logo.png" style="width: 100px" alt="">
          <RouterLink to="">NEWS</RouterLink>
          <RouterLink to="/community">COMMUNITY</RouterLink>
          <RouterLink to="">SHOP</RouterLink>
          <div v-if="loginUser" class="navbar-login">
            <span style="margin-right: 10px">{{ loginUserName }}님 환영합니다</span>
            <div class="text-end">
              <a class="btn btn-outline-light me-2" @click="logout">
                <span>Logout</span>
              </a>
            </div>
<!--            <div class="text-end">-->
<!--              <a class="btn btn-outline-light me-2" @click="goMyPage">-->
<!--                <span>MyPage</span>-->
<!--              </a>-->
<!--            </div>-->
<!--            <div class="text-end">-->
<!--              <a class="btn btn-outline-light me-2" @click="goContactView">-->
<!--                <span>문의</span>-->
<!--              </a>-->
<!--            </div>-->
          </div>
          <div v-else>
            <a @click="GoUserLogin" class="btn btn-outline-light me-2">로그인</a>
            <button @click="GoUserJoin" class="btn btn-warning">회원가입</button>
          </div>
          <img src="@/assets/image_icons/login_icon.png" style="width:40px; margin-left: 50px" alt="">
        </div>
      </nav>
    </header>
  </div>
</template>

<style scoped>
.router-link-exact-active {
  text-decoration: none;
  font-weight: bold;
  font-size: 20px;
  margin-top: 5px;
  color: white;
}

nav {
  box-sizing: border-box;
  background-color: #24418E;
  padding-top: 15px;
  padding-bottom: 15px;
  padding-left: 15px;
  padding-right: 25px;
}
</style>

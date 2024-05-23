<template>
  <div class="d-flex justify-content-center mt-5">
    <div class="container">
      <h4 style="margin-bottom: 20px">게시글 작성</h4>
      <input type="hidden">
      <div class="form-floating mb-3">
        <input type="text" class="form-control" id="title" placeholder="제목" v-model="board.title">
        <label for="title">Title</label>
      </div>
      <div class="form-floating mb-3" v-if="loginUserName">
        <input type="text" :value="loginUserName" class="form-control" id="userId" readonly>
        <label for="userId">writer</label>
      </div>
      <div class="form-floating mb-3">
        <textarea class="form-control" id="content" placeholder="내용" style="height: 200px" v-model="board.content"></textarea>
        <label for="content">Content</label>
      </div>
      <div class="d-flex justify-content-between align-items-center position-relative">
        <button class="btn btn-outline-danger" @click="goBack">취소</button>
        <button class="btn btn-outline-primary" @click="store.createBoard(board)">등록</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref, computed } from "vue";
import { useCommunityStore } from "@/stores/community";
import { useRoute } from "vue-router";
import router from "@/router/index.js";

const goBack = () => {
  router.back();
};

// 반응형 데이터 선언
const loginUserName = ref('')

// 컴포넌트 마운트 시 sessionStorage에서 loginUser 값 로드
const loadUserFromSession = () => {
  const token = sessionStorage.getItem('access-token')
  if (token) {
    const storedUser = token.split('.')
    if (storedUser.length > 1) {
      loginUserName.value = JSON.parse(atob(storedUser[1]))['id']
      board.value.userId = loginUserName.value
    }
  }
}

onMounted(() => {
  loadUserFromSession()
});

const route = useRoute();
const board = ref({
  teamId: route.params.teamId,
  title: "",
  userId: "",
  content: "",
});

const store = useCommunityStore();

</script>

<style scoped>
.container {
  max-width: 60%;
  width: 100%;
  margin-top: 1%;
  padding-top: 5%;
}
</style>

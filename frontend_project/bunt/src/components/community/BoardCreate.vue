<template>
  <div class="d-flex justify-content-center mt-5">
    <div class="container">
      <h4 style="margin-bottom: 20px">게시글 작성</h4>
      <input type="hidden">
      <div class="form-floating mb-3">
        <input type="text" class="form-control" id="title" placeholder="제목" v-model="board.title">
        <label for="title">Title</label>
      </div>
      <div class="form-floating mb-3" v-if="loginUser">
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

// writer = 로그인 유저 아이디
const loginUser = ref(null);
const loginUserName = computed(() => {
  return loginUser.value;
});

onMounted(() => {
  const user = JSON.parse(sessionStorage.getItem('loginUser'));
  if (user) {
    board.value.userId = user.id;
    loginUser.value = user.id;
  }
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
}
</style>

<template>
  <div class="d-flex justify-content-center mt-5">
    <div class="container">
      <h4 style="margin-bottom: 20px">게시글 수정</h4>
      <input type="hidden">
      <div class="form-floating mb-3">
        <input type="text" class="form-control" id="title" placeholder="제목" v-model="store.board.title">
        <label for="title">Title</label>
      </div>
      <div class="form-floating mb-3">
        <input type="text" class="form-control" id="userId" v-model="store.board.userId" readonly>
        <label for="userId">Writer</label>
      </div>
      <div class="form-floating mb-3">
        <textarea class="form-control" id="content" placeholder="내용" style="height: 200px"
                  v-model="store.board.content"></textarea>
        <label for="content">Content</label>
      </div>
      <div class="d-flex justify-content-between align-items-center position-relative">
        <button class="btn btn-outline-danger" @click="goBack">취소</button>
        <button class="btn btn-outline-success" @click="store.updateBoard(route.params.id)">수정 완료</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted } from "vue";
import { useCommunityStore } from "@/stores/community";
import { useRoute } from "vue-router";
import router from "@/router/index.js";

const route = useRoute();
const store = useCommunityStore();

onMounted(() => {
  store.getBoard(route.params.teamId, route.params.id);
});

const goBack = () => {
  router.back();
};
</script>

<style scoped>
.container {
  max-width: 60%;
  width: 100%;
  margin-top: 1%;
  padding-top: 5%;
}
</style>

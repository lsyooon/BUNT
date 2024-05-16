<template>
  <div class="container">
    <h4>게시글 작성</h4>
    <div class="form-floating mb-3">
      <input type="text" class="form-control" id="title" placeholder="제목" v-model="board.title">
      <label for="title">Title</label>
    </div>
    <div class="form-floating mb-3">
            <textarea class="form-control" id="content" placeholder="내용" style="height: 200px"
                      v-model="board.content"></textarea>
      <label for="content">Content</label>
    </div>
    <div class="d-flex justify-content-end">
      <button class="btn btn-outline-primary" @click="createBoard">등록</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useCommunityStore } from "@/stores/community";

const board = ref({
  title: "",
  content: "",
})


const store = useCommunityStore();

const createBoard = function () {
  store.createBoard(board.value)
};
</script>

<style scoped>

</style>
<template>
  <TheHeaderNav/>
  <div class="container">
    <h4>게시글 작성</h4>
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
            <textarea class="form-control" id="content" placeholder="내용" style="height: 200px"
                      v-model="board.content"></textarea>
      <label for="content">Content</label>
    </div>
    <div class="d-flex justify-content-end">
      <button class="btn btn-outline-primary" @click="store.createBoard(board)">등록</button>
    </div>
  </div>
</template>

<script setup>
import {onMounted, ref, computed} from "vue";
import { useCommunityStore } from "@/stores/community";
import {useRoute} from "vue-router";
import TheHeaderNav from "@/components/common/TheHeaderNav.vue";

// writer = 로그인 유저 아이디
const loginUser = ref( );
const loginUserName = computed(() => {
  return loginUser.value;
});

onMounted(() => {
  board.value.userId = JSON.parse(sessionStorage.getItem('loginUser'))['id'];
  loginUser.value = JSON.parse(sessionStorage.getItem('loginUser'))['id'];
});

const route = useRoute();
const board = ref({
  teamId: route.params.teamId,
  title: "",
  userId: "",
  content: "",
})

const store = useCommunityStore();

</script>

<style scoped>

</style>
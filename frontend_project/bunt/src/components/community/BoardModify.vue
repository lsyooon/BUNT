<template>
  <TheHeaderNav/>
  <div class="container">
    <h4>게시글 수정</h4>
    <input type="hidden">
    <div class="form-floating mb-3">
      <input type="text" class="form-control" id="title" placeholder="제목" v-model="store.board.title">
    </div>
    <div class="form-floating mb-3">
      <input type="text" class="form-control" id="userId" v-model="store.board.userId" readonly>
    </div>
    <div class="form-floating mb-3">
            <textarea class="form-control" id="content" placeholder="내용" style="height: 200px"
                      v-model="store.board.content"></textarea>
    </div>
    <div class="d-flex justify-content-end">
      <button class="btn btn-outline-primary" @click="store.updateBoard(route.params.id)">등록</button>
    </div>
  </div>
</template>

<script setup>
import {onMounted } from "vue";
import { useCommunityStore } from "@/stores/community";
import {useRoute} from "vue-router";
import TheHeaderNav from "@/components/common/TheHeaderNav.vue";

const route = useRoute();
const store = useCommunityStore();

onMounted(() => {
  store.getBoard(route.params.teamId, route.params.id);
});

</script>

<style scoped>

</style>
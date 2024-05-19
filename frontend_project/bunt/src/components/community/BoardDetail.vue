<template>
  <div class="container">
    <h4>게시글 상세</h4>
    <hr/>
    <div class="d-flex justify-content-center">
      <div class="card" style="width: 60rem">
        <div class="card-body">
          <div class="mb-3 d-flex justify-content-between align-items-center" style="padding-top: 2%">
            <h4 class="card-title">{{ store.board.title }}</h4>
            <div class="d-flex justify-content-end">
              <h6 class="card-subtitle text-body-secondary">작성자 : {{ store.board.userId }}　　|　</h6>
              <h6 class="card-subtitle text-body-secondary">작성일 : {{ store.board.regDate }}</h6>
            </div>
          </div>
          <hr/>
          <p class="card-text" style="height: 10rem">
            {{ store.board.content }}
          </p>
          <div v-if="loginUser !== null">
            <div v-if="loginUserName === store.board.userId">
              <div class="d-flex justify-content-between">
                <button class="mx-3 btn btn-light" @click="goBack">돌아가기</button>
                <div>
                  <button class="mx-3 btn btn-outline-warning" @click="moveUpdate">수정</button>
                  <button class="mx-3 btn btn-outline-danger" @click="deleteBoard">삭제</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <CommentList/>
</template>

<script setup>
import {useRoute, useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import {useCommunityStore} from "@/stores/community";
import axios from "axios";
import CommentList from "@/components/community/CommentList.vue"

const route = useRoute();
const router = useRouter();
const store = useCommunityStore();

onMounted(() => {
  store.getBoard(route.params.teamId, route.params.id);
});

const goBack = () => {
  router.back();
};

const moveUpdate = function () {
  router.push({name: "boardModify"});
};

const deleteBoard = function () {
  axios
      .delete(`http://localhost:8080/api/board/${route.params.teamId}/${route.params.id}`)
      .then(() => {
        router.push({name: "community"});
      })
      .catch(() => {
      });
};

const loginUser = ref(null);
const loginUserName = ref('');
onMounted(() => {
  const storedUser = sessionStorage.getItem('loginUser');
  if (storedUser) {
    loginUser.value = JSON.parse(storedUser);
    loginUserName.value = loginUser.value.id; // 사용자 이름 속성 사용
  }
});
</script>

<style scoped>

.container {
  padding-top: 3%;
}

</style>

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
          <div class="d-flex align-items-center position-relative">
            <button class="mx-3 btn btn-light back" @click="goBack">목록</button>
            <div class="position-absolute start-50 translate-middle-x">
              <button class="mx-3 btn btn-light like" @click="toggleLike"
                      :style="{ visibility: loginUser === null ? 'hidden' : 'visible' }">
                <img :src="likeImage()" style="width: 30px; height: 30px">
                좋아요 <span v-if="likeCount !== 0">{{likeCount}}</span>
              </button>
            </div>
            <div class="ms-auto">
              <div v-if="loginUser !== null && loginUserName === store.board.userId" class="d-flex">
                <button class="mx-3 btn btn-outline-warning" @click="moveUpdate">수정</button>
                <button class="mx-3 btn btn-outline-danger" @click="deleteBoard">삭제</button>
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
import { onMounted, ref} from "vue";
import {useRoute, useRouter} from "vue-router";
import {useCommunityStore} from "@/stores/community";
import axios from "axios";
import CommentList from "@/components/community/CommentList.vue";

const route = useRoute();
const router = useRouter();
const store = useCommunityStore();

const isLiked = ref(false);
const likeCount = ref(0);

onMounted(() => {
  store.getBoard(route.params.teamId, route.params.id);
  checkLiked();
});

const goBack = () => {
  router.back();
};

const moveUpdate = () => {
  router.push({name: "boardModify"});
};

const deleteBoard = () => {
  if (confirm("게시글을 삭제하시겠습니까?")) {
    axios
        .delete(`http://localhost:8080/api/board/${route.params.teamId}/${route.params.id}`)
        .then(() => {
          router.push({name: "community"});
        })
        .catch(() => {
          // Handle error if necessary
        });
  }
};

const loginUser = ref(null);
const loginUserName = ref("");
onMounted(() => {
  const storedUser = sessionStorage.getItem("loginUser");
  if (storedUser) {
    loginUser.value = JSON.parse(storedUser);
    loginUserName.value = loginUser.value.id;
  }
});

const boardId = route.params.id;
const checkLiked = async () => {
  const response = await axios.get(`http://localhost:8080/api/read/likeList/${JSON.parse(sessionStorage.getItem("loginUser"))['id']}/${boardId}`);
  isLiked.value = response.data === 1;
  likeCnt(); // 좋아요 여부 확인 후 좋아요 수 업데이트
};

// 좋아요 토글 함수
const toggleLike = async () => {
  const response = await axios.post(`http://localhost:8080/api/read/likeList/${JSON.parse(sessionStorage.getItem("loginUser"))['id']}/${boardId}`);
  const like = response.data;

  isLiked.value = like === 1;
  likeCnt(); // 좋아요 상태 변경 후 좋아요 수 업데이트
};

const likeCnt = async () => {
  const response = await axios.get(`http://localhost:8080/api/read/likeList/cnt/${boardId}`);
  likeCount.value = response.data;
};

import fullLikeImage from "@/assets/image_icons/fullLike.png";
import emptyLikeImage from "@/assets/image_icons/emptyLike.png";

const likeImage = () => {
  return isLiked.value ? fullLikeImage : emptyLikeImage;
};
</script>



<style scoped>

.container {
  padding-top: 3%;
}

</style>

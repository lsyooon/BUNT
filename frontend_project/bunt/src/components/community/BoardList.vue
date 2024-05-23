<script setup>
import { useCommunityStore } from "@/stores/community.js";
import { computed, onMounted, ref } from "vue";
import BoardSearchInput from "@/components/community/BoardSearchInput.vue";
import { useRoute } from "vue-router";
import router from "@/router/index";
import axios from "axios";

const store = useCommunityStore();
const route = useRoute();

onMounted(async () => {
  await store.getBoardList(route.params.teamId);
});

const perPage = 10;
const currentPage = ref(1);

const pageCount = computed(() => {
  return Math.ceil(store.boardList.length / perPage);
});

const clickPage = function(page) {
  currentPage.value = page;
};

const currentPageBoardList = computed(() => {
  return store.boardList.slice(
    (currentPage.value - 1) * perPage,
    currentPage.value * perPage
  );
});

const loginUser = ref(null);
const loginUserName = ref('');

const loadUserFromToken = () => {
  const token = sessionStorage.getItem('access-token');
  if (token) {
    const payload = JSON.parse(atob(token.split('.')[1]));
    loginUser.value = payload;
    loginUserName.value = payload.id;
  }
};

onMounted(() => {
  loadUserFromToken();
});

const linkToBoardDetail = (communityBoardId) => {
  return `/${route.params.teamId}/community/${communityBoardId}`;
};

const linkToBoardCreate = () => {
  const teamId = route.params.teamId;
  router.push(`/${teamId}/community/create`);
};
</script>

<template>
  <div class="container mt-5">
    <div class="bar mb-3">
      <div class="button-container" :style="{ visibility: loginUser === null ? 'hidden' : 'visible' }">
        <button class="btn btn-primary custom-btn" @click="linkToBoardCreate">글쓰기</button>
      </div>
      <BoardSearchInput />
    </div>
    <table class="table table-hover text-center">
      <thead class="thead-light">
      <tr>
        <th>글 번호</th>
        <th>제목</th>
        <th>글쓴이</th>
        <th>등록일</th>
        <th>추천수</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="board in currentPageBoardList" :key="board.communityBoardId">
        <td>{{ board.communityBoardId }}</td>
        <td>
          <RouterLink :to="linkToBoardDetail(board.communityBoardId)" class="nav-link custom-link">{{ board.title }}</RouterLink>
        </td>
        <td>{{ board.userId }}</td>
        <td>{{ board.regDate }}</td>
        <td>{{ board.likeCount }}</td>
      </tr>
      </tbody>
    </table>
    <nav aria-label="Page navigation">
      <ul class="pagination d-flex justify-content-center">
        <li class="page-item">
          <a
            class="page-link custom-btn"
            @click.prevent="currentPage--"
            :class="{ disabled: currentPage <= 1 }"
            href="#"
          >&lt;</a>
        </li>
        <li
          class="page-item"
          :class="{ active: currentPage === page }"
          v-for="page in pageCount"
          :key="page"
        >
          <a class="page-link custom-btn" href="#" @click.prevent="clickPage(page)">{{ page }}</a>
        </li>
        <li class="page-item">
          <a
            class="page-link custom-btn"
            @click.prevent="currentPage++"
            :class="{ disabled: currentPage >= pageCount }"
            href="#"
          >&gt;</a>
        </li>
      </ul>
    </nav>
  </div>
  <RouterView />
</template>

<style scoped>
table, tr, td, th {
  border: 1px solid #dee2e6;
}

.container {
  padding-top: 5%;
}

.bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.button-container {
  display: flex;
  align-items: center;
}

.custom-btn {
  background-color: #e0e7ff !important; /* 부드러운 파스텔 블루 */
  color: #4c4c4c !important; /* 텍스트 색상 */
  border-color: #e0e7ff !important; /* 테두리 색상 */
  transition: background-color 0.3s ease;
}

.custom-btn:hover {
  background-color: #c3dafe !important; /* 호버 상태에서 조금 더 어두운 파스텔 블루 */
}

.page-item.active .custom-btn {
  background-color: #a3bffa !important; /* 활성화된 페이지의 조금 더 어두운 파스텔 블루 */
  border-color: #a3bffa !important;
}

.page-link.custom-btn.disabled {
  background-color: #f1f3f5 !important;
  border-color: #f1f3f5 !important;
  color: #adb5bd !important;
}

.custom-link {
  color: #4c4c4c !important; /* 링크 색상을 부드러운 회색으로 변경 */
}

.custom-link:hover {
  color: #1c7ed6 !important; /* 링크 호버 색상을 밝은 블루로 변경 */
}
</style>

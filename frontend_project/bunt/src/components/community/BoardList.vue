<script setup>
import {useCommunityStore} from "@/stores/community.js";
import {computed, onMounted, ref} from "vue";
import BoardSearchInput from "@/components/community/BoardSearchInput.vue";
import {useRoute} from "vue-router";
import router from "@/router/index";

const store = useCommunityStore();

const route = useRoute();

onMounted(() => {
  store.getBoardList(route.params.teamId);
});

const perPage = 10;
const currentPage = ref(1);

const pageCount = computed(() => {
  return Math.ceil(store.boardList.length / perPage);
});

const clickPage = function (page) {
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
onMounted(() => {
  const storedUser = sessionStorage.getItem('loginUser');
  if (storedUser) {
    loginUser.value = JSON.parse(storedUser);
    loginUserName.value = loginUser.value.id; // 사용자 이름 속성 사용
  }
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
  <div class="container">
    <div class="bar">
      <div v-if="loginUser !== null">
        <button class="btn btn-outline-primary" @click="linkToBoardCreate">글쓰기</button>
      </div>
      <BoardSearchInput/>
    </div>
    <table class="table table-hover text-center">
      <th>번호</th>
      <th>제목</th>
      <th>글쓴이</th>
      <th>등록일</th>
      <tr v-for="board in currentPageBoardList" :key="board.community_board_id">
        <td>{{ board.communityBoardId }}</td>
        <td>
          <RouterLink :to="linkToBoardDetail(board.communityBoardId)" class="nav-link">{{ board.title }}</RouterLink>
        </td>
        <td>{{ board.userId }}</td>
        <td>{{ board.regDate }}</td>
      </tr>
    </table>
    <nav aria-label="Page navigation">
      <ul class="pagination d-flex justify-content-center">
        <li class="page-item">
          <a
              class="page-link"
              @click.prevent="currentPage--"
              :class="{ disabled: currentPage <= 1 }"
              href="#"
          >&lt;</a
          >
        </li>
        <li
            class="page-item"
            :class="{ active: currentPage === page }"
            v-for="page in pageCount"
            :key="page"
        >
          <a class="page-link" href="#" @click.prevent="clickPage(page)">{{
              page
            }}</a>
        </li>
        <li class="page-item">
          <a
              class="page-link"
              @click.prevent="currentPage++"
              :class="{ disabled: currentPage >= pageCount }"
              href="#"
          >&gt;</a
          >
        </li>
      </ul>
    </nav>
  </div>
  <RouterView/>
</template>

<style scoped>
table, tr, td, th {
  border: 1px solid black;
}

.container {
  padding-top: 3%;
}

.bar {
  display: flex;
  justify-content: space-between;
}

</style>
<script setup>
import TheHeaderNav from "@/components/common/TheHeaderNav.vue";
import {useCommunityStore} from "@/stores/community.js";
import {computed, onMounted, ref} from "vue";
import BoardSearchInput from "@/components/community/BoardSearchInput.vue";

const store = useCommunityStore();
onMounted(() => {
  store.getBoardList();
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
</script>

<template>
  <TheHeaderNav/>
  <div>
    <BoardSearchInput />

    <button @click="$router.push('/community/create')">글쓰기</button>
    <table class="table table-hover text-center">
      <th>번호</th>
      <th>제목</th>
      <th>글쓴이</th>
      <th>등록일</th>
      <tr v-for="board in currentPageBoardList" :key="board.community_board_id">
        <td>{{ board.communityBoardId }}</td>
        <td><RouterLink :to="`/community/${board.communityBoardId}`">{{board.title}}</RouterLink></td>
        <td>{{ board.userId }}</td>
        <td>{{ board.regDate }}</td>
      </tr>
    </table>
  </div>
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
  <RouterView/>
</template>

<style scoped>
table, tr, td, th{
  border: 1px solid black;
}
</style>
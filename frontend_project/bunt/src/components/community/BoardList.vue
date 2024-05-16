<script setup>
import TheHeaderNav from "@/components/common/TheHeaderNav.vue";
import {useCommunityStore} from "@/stores/community.js";
import {onMounted} from "vue";

const store = useCommunityStore()
onMounted(() => {
  store.getBoardList()
})
</script>

<template>
  <TheHeaderNav/>
  <div>
    <table>
      <th>번호</th>
      <th>제목</th>
      <th>글쓴이</th>
      <th>등록일</th>
      <tr v-for="board in store.boardList" :key="board.boardId">
        <td>{{ board.communityId }}</td>
        <td>{{ board.title }}</td>
        <td>{{ board.userId }}</td>
        <td></td>
      </tr>
    </table>
  </div>
</template>

<style scoped>
table, tr, td, th{
  border: 1px solid black;
}
</style>
<template>
  <div class="text-center search-container">
    <div class="row align-items-center">
      <div class="col-3">
        <select class="form-select custom-select" v-model="searchInfo.key">
          <option value="none">전체</option>
          <option value="title">제목</option>
          <option value="user_id">작성자</option>
          <option value="content">내용</option>
        </select>
      </div>
      <div class="col-6">
        <input type="text" class="form-control custom-input" v-model="searchInfo.word" />
      </div>

      <div class="col-1">
        <button class="btn btn-primary custom-btn" @click="searchBoardList" style="width: 70px">검색</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useCommunityStore } from "@/stores/community";
import { useRoute } from "vue-router";

const store = useCommunityStore();
const searchInfo = ref({
  key: "none",
  word: "",
});

const route = useRoute();
const searchBoardList = function() {
  store.searchBoardList(searchInfo.value, route.params.teamId);
};
</script>

<style scoped>
.search-container {
  margin-top: 20px;
  margin-bottom: 20px;
}

.custom-select {
  background-color: #f5f8fc; /* 부드러운 파스텔 블루 */
  color: #4c4c4c; /* 텍스트 색상 */
  border: 1px solid #ced4da; /* 테두리 색상 */
}

.custom-select:focus {
  border-color: #86b7fe; /* 포커스 시 테두리 색상 */
  box-shadow: 0 0 0 0.2rem rgba(13, 110, 253, 0.25); /* 포커스 시 그림자 */
}

.custom-input {
  background-color: #f5f8fc; /* 부드러운 파스텔 블루 */
  color: #4c4c4c; /* 텍스트 색상 */
  border: 1px solid #ced4da; /* 테두리 색상 */
}

.custom-input:focus {
  border-color: #86b7fe; /* 포커스 시 테두리 색상 */
  box-shadow: 0 0 0 0.2rem rgba(13, 110, 253, 0.25); /* 포커스 시 그림자 */
}

.custom-btn {
  background-color: #4c6ef5 !important; /* 부드러운 파스텔 블루 */
  color: white !important; /* 텍스트 색상 */
  border-color: #4c6ef5 !important; /* 테두리 색상 */
  transition: background-color 0.3s ease;
}

.custom-btn:hover {
  background-color: #364fc7 !important; /* 호버 상태에서 약간 더 진한 파스텔 블루 */
}
</style>

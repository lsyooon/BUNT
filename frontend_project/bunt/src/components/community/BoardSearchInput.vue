<template>
    <div class="text-center">
        <div class="row align-items-center">
            <div class="col-2">
                <select class="form-select" v-model="searchInfo.key">
                    <option value="none">전체</option>
                    <option value="title">제목</option>
                    <option value="content">내용</option>
                </select>
            </div>
            <div class="col-6">
                <input type="text" class="form-control" v-model="searchInfo.word" />
            </div>

            <div class="col-1">
                <button class="btn btn-outline-warning" @click="searchBoardList">검색</button>
            </div>
        </div>
    </div>
</template>
  
<script setup>
import { ref } from "vue";
import { useCommunityStore } from "@/stores/community";

const store = useCommunityStore();
const searchInfo = ref({
    key: "none",
    word: "",
    orderBy: "none",
    orderByDir: "asc",
});
const searchBoardList = function () {
    store.searchBoardList(searchInfo.value);
};

</script>
  
<style scoped></style>
  
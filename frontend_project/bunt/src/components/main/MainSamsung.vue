<template>
  <div>
    <div class="row">
      <div class="main-top">
        <div class="main-left">
          <img class="background-img">
          <button @click="searchGuJaWook">구자욱</button>
          <button @click="searchKimheonhon">김헌곤</button>
          <button @click="searchMackinnon">맥키넌</button>
          <button @click="searchKimhero">김영웅</button>
          <button @click="searchRyuji">류지혁</button>
          <button @click="searchLeejaehyun">이재현</button>
          <button @click="searchMinho">강민호</button>
          <button @click="searchByeongwoo">전병우</button>
          <button @click="searchKimjichan">김지찬</button>
        </div>
        <div class="main-right">
          <VideoDetail v-if="store.selectedVideo"/>
        </div>
      </div>
      <div v-if="false">
        <input type="text" v-model="keyword">
        <button @click="search">검색</button>
      </div>
      <team-rank></team-rank>
    </div>
  </div>
</template>

<style scoped>

.row {
  margin: 0;
}

.main-top {
  display: flex;
}

.main-left {
  flex: 6;
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: flex-end; /* 버튼들을 오른쪽 끝으로 정렬 */
}

.background-img {
  width: 100px;
  height: 100px;
  background-image: url('@/assets/image_logo/SAMSUNG_logo.png');
  background-size: cover;
  border: 1px solid rgba(0, 0, 0, 0.1);
  z-index: -1;
}

.main-left button {
  margin-bottom: 10px; /* 버튼들 사이에 간격 설정 */
  z-index: 1;
}

.main-right {
  flex: 4;
}
</style>

<script setup lang="ts">
import {ref, onMounted } from 'vue';
import { useYoutubeStore } from '@/stores/youtube.js';
import TeamRank from '@/components/main/teamRank.vue';
import VideoDetail from '@/components/main/YoutubeDetail.vue';

const store = useYoutubeStore();
const keyword = ref('');

const search = () => {
  store.youtubeSearch(keyword.value);
};

// 페이지 처음 들어왔을 때
onMounted(() => {
  // 초기 검색어 설정
  keyword.value = '삼성라이온즈 응원가';
  // 초기 검색 실행
  search();
});

const searchGuJaWook = () => {
  keyword.value = '구자욱 응원가';
  search();
};

const searchKimheonhon = () => {
  keyword.value = '김헌곤 응원가';
  search();
};

const searchMackinnon = () => {
  keyword.value = '삼성라이온즈 맥키넌 응원가';
  search();
};

const searchKimhero = () => {
  keyword.value = '삼성라이온즈 김영웅 응원가';
  search();
};

const searchRyuji = () => {
  keyword.value = '삼성라이온즈 류지혁 응원가';
  search();
};

const searchLeejaehyun = () => {
  keyword.value = '삼성라이온즈 이재현 응원가';
  search();
};

const searchMinho = () => {
  keyword.value = '삼성라이온즈 강민호 응원가';
  search();
};

const searchByeongwoo = () => {
  keyword.value = '삼성라이온즈 전병우 응원가';
  search();
};

const searchKimjichan = () => {
  keyword.value = '김지찬 응원가';
  search();
};

</script>

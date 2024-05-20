<template>
  <div class="cont">
    <div class="row">
      <div class="main-top">
        <div class="main-left">
          <img class="background-img">
          <div class="buttons"> <!-- 버튼들을 감싸는 div 추가 -->
            <button @click="searchGuJaWook"><p>LF　　　구자욱　　　5</p></button>
            <button @click="searchKimheonhon"><p>RF　　　김헌곤　　　32</p></button>
            <button @click="searchMackinnon"><p>1B　　　맥키넌　　　24</p></button>
            <button @click="searchKimhero"><p>DH　　　김영웅　　　30</p></button>
            <button @click="searchRyuji"><p>2B　　　류지혁　　　16</p></button>
            <button @click="searchLeejaehyun"><p>SS　　　이재현　　　7</p></button>
            <button @click="searchMinho"><p>C　　　강민호　　　47</p></button>
            <button @click="searchByeongwoo"><p>3B　　　전병우　　　34</p></button>
            <button @click="searchKimjichan"><p>CF　　　김지찬　　　58</p></button>
            <button @click="searchReyes"><p>SP　　　레예스　　　43</p></button>
          </div>
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
.cont {
  margin-top: 3%;
}

.row {
  margin: 0;
}

.main-top {
  display: flex;
  margin-bottom: 0.5%;
}

.main-left {
  flex: 6;
  position: relative;
  display: flex;
  flex-direction: column;
  padding-right: 3%;
  padding-left: 1%;
}

.background-img {
  width: 440px;
  height: 315px;
  background-image: url('@/assets/image_logo/SAMSUNG_logo.png');
  position: absolute;
  background-size: cover;
  z-index: -1;
  border: none;
}

.main-left button {
  transform: skewX(-45deg);
  display:block;
  width:450px;
  height:30px;
  text-align: center;
  margin-bottom: 5.5px;
  line-height: 40px;
  border: none;
  background-color: lightgray;
  opacity : 0.8;
}
.main-left button:hover {
  opacity: 1;
  background-color: gray;
}
.main-left button p {
  transform: skewX(45deg);
}

.main-right {
  flex: 4;
}

.buttons {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
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

const searchReyes = () => {
  keyword.value = '삼성라이온즈 레예스';
  search();
};
</script>
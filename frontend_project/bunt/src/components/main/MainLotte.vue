<template>
  <div class="cont">
    <div class="row">
      <div class="main-top">
        <div class="main-left">
          <img class="background-img">
          <div class="buttons"> <!-- 버튼들을 감싸는 div 추가 -->
            <button @click="searchHwangsungbin"><p>LF　　　황성빈　　　0</p></button>
            <button @click="searchYundonghee"><p>CF　　　윤동희　　　91</p></button>
            <button @click="searchGoseungmin"><p>2B　　　고승민　　　65</p></button>
            <button @click="searchRayes"><p>RF　　　레이예스　　　29</p></button>
            <button @click="searchHandonghee"><p>DH　　　한동희　　　25</p></button>
            <button @click="searchNaseung"><p>1B　　　나승엽　　　51</p></button>
            <button @click="searchParkuk"><p>3B　　　박승욱　　　53</p></button>
            <button @click="searchYugangnam"><p>C　　　유강남　　　27</p></button>
            <button @click="searchLeeju"><p>SS　　　이학주　　　7</p></button>
            <button @click="searchWks"><p>SP　　　윌커슨　　　46</p></button>
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
  width: 445px;
  height: 315px;
  background-image: url('@/assets/image_logo/LOTTE_logo.png');
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

const searchHwangsungbin = () => {
  keyword.value = '롯데자이언츠 황성빈 응원가';
  search();
};

const searchYundonghee = () => {
  keyword.value = '롯데자이언츠 윤동희 응원가';
  search();
};

const searchGoseungmin = () => {
  keyword.value = '롯데자이언츠 고승민 응원가';
  search();
};

const searchRayes = () => {
  keyword.value = '롯데자이언츠 레이예스 응원가';
  search();
};

const searchHandonghee = () => {
  keyword.value = '롯데자이언츠 한동희 응원가';
  search();
};

const searchNaseung = () => {
  keyword.value = '롯데자이언츠 나승엽 응원가';
  search();
};

const searchParkuk = () => {
  keyword.value = '롯데자이언츠 박승욱 응원가';
  search();
};

const searchYugangnam = () => {
  keyword.value = '롯데자이언츠 유강남 응원가';
  search();
};

const searchLeeju = () => {
  keyword.value = '롯데자이언츠 이학주 응원가';
  search();
};

const searchWks = () => {
  keyword.value = '롯데자이언츠 윌커슨';
  search();
};
</script>
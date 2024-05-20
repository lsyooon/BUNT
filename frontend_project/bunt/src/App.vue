<script setup>
import { RouterView } from 'vue-router';
import Footer from "@/components/common/Footer.vue";
import TheHeaderNav from "@/components/common/TheHeaderNav.vue";
import { ref, onMounted, onUnmounted } from 'vue';

// 스크롤 상태를 추적하기 위한 상태 변수
const isHeaderVisible = ref(true);

// 스크롤 이벤트 핸들러
const handleScroll = () => {
  isHeaderVisible.value = window.scrollY === 0;
};

onMounted(() => {
  window.addEventListener('scroll', handleScroll);
});

onUnmounted(() => {
  window.removeEventListener('scroll', handleScroll);
});
</script>

<template>
  <div id="app">
    <TheHeaderNav v-if="isHeaderVisible" />
    <main>
      <RouterView />
    </main>
    <Footer />
  </div>
</template>

<style scoped>
/* 모든 요소의 기본 여백과 패딩을 제거 */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

/* body와 html 요소의 여백과 패딩 제거 */
html, body {
  margin: 0;
  padding: 0;
  height: 100%;
  overflow: hidden; /* 전체 페이지에서 스크롤을 없앱니다 */
}

/* TheHeaderNav 스타일 조정 */
TheHeaderNav {
  height: 50px; /* TheHeaderNav 높이 조정 */
  background-color: white; /* 필요에 따라 배경색 추가 */
  z-index: 1000; /* 다른 요소보다 위에 오도록 z-index 추가 */
  width: 100%;
  position: fixed;
  top: 0;
}

/* main 영역의 스타일 조정 */
main {
  flex: 1;
  overflow-y: auto; /* main 영역에서만 스크롤이 가능하도록 설정 */
  padding-top: 50px; /* TheHeaderNav 높이만큼 패딩 추가 */
  padding-bottom: 100px; /* Footer 높이만큼 패딩 추가 */
}

/* Footer 스타일 조정 */
footer {
  position: fixed;
  bottom: 0;
  width: 100%;
  height: 100px; /* Footer 높이 */
  background-color: white; /* 필요에 따라 배경색 추가 */
}

/* 추가 스타일 */
#app {
  display: flex;
  flex-direction: column;
  height: 100vh; /* 전체 높이를 100vh로 설정 */
}
</style>

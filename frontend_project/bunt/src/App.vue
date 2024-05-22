<script setup>
import { RouterView, useRoute } from 'vue-router';
import Footer from "@/components/common/Footer.vue";
import TheHeaderNav from "@/components/common/TheHeaderNav.vue";
import { ref, onMounted, onUnmounted } from 'vue';

// 현재 라우트를 가져오기 위한 useRoute 훅 사용
const route = useRoute();

// 헤더와 푸터의 가시성을 추적하기 위한 상태 변수
const isHeaderVisible = ref(true);
const isFooterVisible = ref(false);

// 스크롤 이벤트 핸들러
const handleScroll = () => {
  // 스크롤 위치가 최상단일 때만 헤더를 표시
  isHeaderVisible.value = window.scrollY === 0;
  // 스크롤 위치가 문서의 맨 아래에 도달했을 때 푸터를 표시
  isFooterVisible.value = window.innerHeight + window.scrollY >= document.body.offsetHeight;
};

onMounted(() => {
  // 컴포넌트가 마운트되었을 때 스크롤 이벤트 리스너 추가
  window.addEventListener('scroll', handleScroll);
  handleScroll(); // 초기 상태 설정
});

onUnmounted(() => {
  // 컴포넌트가 언마운트되었을 때 스크롤 이벤트 리스너 제거
  window.removeEventListener('scroll', handleScroll);
});
</script>

<template>
  <div id="app">
    <!-- route.meta.hideHeaderFooter 값에 따라 TheHeaderNav 컴포넌트의 표시 여부 결정 -->
    <TheHeaderNav v-if="!route.meta.hideHeaderFooter && isHeaderVisible" />
    <main>
      <RouterView />
    </main>
    <Footer v-if="!route.meta.hideHeaderFooter && isFooterVisible" />
  </div>
</template>

<style scoped>
/* body와 html 요소의 여백과 패딩 제거 */
html, body {
  margin: 0;
  padding: 0;
  height: 100%;
  width: 100%;
  overflow: hidden; /* 전체 페이지에서 스크롤을 가능하게 설정 */
}

/* TheHeaderNav 스타일 조정 */
TheHeaderNav {
  height: 50px; /* TheHeaderNav 높이 설정 */
  z-index: 1000; /* 다른 요소보다 위에 오도록 z-index 설정 */
  width: 100%;
  position: fixed; /* 고정 위치 설정 */
  top: 0;
}

/* main 영역의 스타일 조정 */
main {
  flex: 1;
  width: 100%; /* 전체 너비를 100%로 설정 */
  overflow-y: auto; /* 세로 스크롤을 가능하게 설정 */
}

/* Footer 스타일 조정 */
footer {
  position: fixed; /* 고정 위치 설정 */
  bottom: 0;
  left: 0; /* 왼쪽 하단에 고정 */
  width: 100%;
  height: 100px; /* Footer 높이 설정 */
  background-color: white; /* 필요에 따라 배경색 추가 */
  z-index: 1000; /* 다른 요소보다 위에 오도록 z-index 설정 */
}

/* 추가 스타일 */
#app {
  display: flex;
  flex-direction: column;
  height: 100vh; /* 전체 높이를 100vh로 설정 */
  width: 100%; /* 전체 너비를 100vw로 설정 */
  overflow: hidden; /* 내부에서 스크롤이 발생하지 않도록 설정 */
}
</style>

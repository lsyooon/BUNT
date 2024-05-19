<script setup>
import { RouterView } from 'vue-router';
import Footer from "@/components/common/Footer.vue";
import TheHeaderNav from "@/components/common/TheHeaderNav.vue";
</script>

<template>
  <TheHeaderNav />
  <main>
    <RouterView />
  </main>
  <Footer />
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
}

TheHeaderNav {
  position: fixed;
  top: 0;
  width: 100%;
  height: 100px; /* TheHeaderNav 높이 */
  background-color: white; /* 필요에 따라 배경색 추가 */
  z-index: 1000; /* 다른 요소보다 위에 오도록 z-index 추가 */
}

main {
  padding-top: 100px; /* TheHeaderNav 높이만큼 패딩 추가 */
  padding-bottom: 100px; /* Footer 높이만큼 패딩 추가 */
  min-height: calc(100vh - 200px); /* 화면 전체 높이에서 Header와 Footer 높이를 뺀 값 */
}

footer {
  position: fixed;
  bottom: 0;
  width: 100%;
  height: 100px; /* Footer 높이 */
  background-color: white; /* 필요에 따라 배경색 추가 */
  z-index: 1000; /* 다른 요소보다 위에 오도록 z-index 추가 */
}
</style>

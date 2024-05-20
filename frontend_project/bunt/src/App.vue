<script setup>
import { RouterView } from 'vue-router';
import Footer from "@/components/common/Footer.vue";
import TheHeaderNav from "@/components/common/TheHeaderNav.vue";
import { ref, onMounted, onUnmounted } from 'vue';

// 스크롤 상태를 추적하기 위한 상태 변수
const isHeaderVisible = ref(true);
const isFooterVisible = ref(false);

// 스크롤 이벤트 핸들러
const handleScroll = () => {
  isHeaderVisible.value = window.scrollY === 0;
  isFooterVisible.value = window.innerHeight + window.scrollY >= document.body.offsetHeight;
};

onMounted(() => {
  window.addEventListener('scroll', handleScroll);
  handleScroll(); // 초기 상태 설정
});

onUnmounted(() => {
  window.removeEventListener('scroll', handleScroll);
});

const isChecked = ref(false);

onMounted(() => {
  if (window.location.pathname === '/') {
    isChecked.value = true;
  }
});


</script>

<template>
  <div id="app">
    <TheHeaderNav v-if="isHeaderVisible && !isChecked"/>
    <main>
      <RouterView />
    </main>
    <Footer v-if="isFooterVisible && !isChecked" />
  </div>
</template>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html, body {
  margin: 0;
  padding: 0;
  height: 100%;
  overflow: auto;
}

TheHeaderNav {
  height: 50px;
  background-color: white;
  z-index: 1000;
  width: 100%;
  position: fixed;
  top: 0;
}

main {
  flex: 1;
  padding-top: 50px;
}

footer {
  position: fixed;
  bottom: 0;
  right: 0;
  width: 100%;
  height: 100px;
  background-color: white;
  z-index: 1000;
}

#app {
  display: flex;
  flex-direction: column;
  height: 100vh;
}
</style>

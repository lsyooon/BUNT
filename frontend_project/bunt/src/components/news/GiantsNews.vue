<template>
  <div id="home" class="d-flex align-items-center justify-content-center min-vh-100">
    <div class="container mt-5 animate__animated animate__fadeInDown">
      <h1 class="mb-4">뉴스 목록</h1>
      <div v-if="loading" class="alert alert-info">응답을 기다리는 중...</div>
      <div v-else>
        <ul class="list-group">
          <li v-for="(item, index) in newsItems" :key="index" class="list-group-item d-flex justify-content-between align-items-center">
            <router-link :to="{ name: 'newsDetail', params: { teamId: '1' }, query: { link: item.link } }" class="text-decoration-none">
              <strong>{{ item.title }}</strong>
            </router-link>
            <span class="badge bg-secondary">{{ item.date }}</span>
          </li>
        </ul>
      </div>
      <div class="mt-3 d-flex justify-content-between">
        <button @click="goToPreviousPage" :disabled="currentPage === 1" class="btn btn-outline-primary">&lt; 이전 페이지</button>
        <button @click="fetchNews" class="btn btn-primary">뉴스 가져오기</button>
        <button @click="goToNextPage" :disabled="currentPage === totalPages" class="btn btn-outline-primary">다음 페이지 &gt;</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import { useNewsStore } from '@/stores/giantsNews.js'

const newsStore = useNewsStore();
const loading = ref(false);

const fetchNews = async () => {
  loading.value = true;
  await newsStore.fetchNews();
  loading.value = false;
};

const goToPreviousPage = async () => {
  loading.value = true;
  await newsStore.goToPreviousPage();
  loading.value = false;
};

const goToNextPage = async () => {
  loading.value = true;
  await newsStore.goToNextPage();
  loading.value = false;
};

onMounted(() => {
  fetchNews();
});

const newsItems = computed(() => newsStore.newsItems);
const currentPage = computed(() => newsStore.currentPage);
const totalPages = computed(() => newsStore.totalPages);
</script>

<style scoped>
@import 'https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css';

#home {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  color: #2c3e50;
}

.container {
  max-width: 800px;
  background-color: white;
  border-radius: 0.5rem;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 2rem;
}

.list-group-item {
  background-color: #f8f9fa; /* 리스트 아이템 배경색 */
  border: 1px solid #dee2e6; /* 리스트 아이템 경계선 색 */
  border-radius: 0.25rem; /* 리스트 아이템 모서리 둥글게 */
  transition: transform 0.2s; /* 호버 효과 추가 */
}

.list-group-item:hover {
  transform: scale(1.02); /* 호버 시 살짝 확대 */
}

.text-decoration-none {
  color: midnightblue; /* 링크 색상 */
}

.text-decoration-none:hover {
  text-decoration: underline; /* 호버 시 밑줄 */
}

.badge {
  font-size: 0.9em;
}

.mt-5 {
  margin-top: 3rem;
}

.mt-3 {
  margin-top: 1rem;
}

button:disabled {
  cursor: not-allowed;
  opacity: 0.65;
  background-color: white;
  border: white;
  color: white;
}

.btn-outline-primary, .btn-primary {
  margin: 0 5px;
  border-color: midnightblue;
}

.btn-outline-primary {
  color: midnightblue; /* 텍스트 색상 변경 */
}

.btn-outline-primary:hover {
  background-color: midnightblue;
  color: white;
}

.btn-primary {
  background-color: midnightblue;
  border-color: midnightblue;
}

.btn-primary:hover {
  background-color: steelblue;
  border-color: steelblue;
}
</style>

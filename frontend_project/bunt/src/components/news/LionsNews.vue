<template>
  <div id="home" class="d-flex align-items-center justify-content-center min-vh-100">
    <div class="container mt-5">
      <h1 class="mb-4">라이온즈 뉴스 목록</h1>
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
import { useNewsStore } from '@/stores/lionsNews.js'

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
#home {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  color: #2c3e50;
}

.container {
  max-width: 800px;
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
  color: #007bff; /* 링크 색상 */
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
}
</style>

<template>
  <div id="app" class="container mt-5">
    <h1 class="mb-4">뉴스 목록</h1>
    <div v-if="loading" class="alert alert-info">응답을 기다리는 중...</div>
    <div v-else>
      <ul class="list-group">
        <li v-for="(item, index) in newsItems" :key="index" class="list-group-item">
          <strong>{{ item.title }}</strong>
          <span class="badge bg-secondary">{{ item.date }}</span>
        </li>
      </ul>
    </div>
    <button @click="fetchNews" class="btn btn-primary mt-3">뉴스 가져오기</button>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import { useNewsStore } from '@/util/news.js'

const newsStore = useNewsStore();
const loading = ref(false);

const fetchNews = async () => {
  loading.value = true;
  await newsStore.fetchNews();
  loading.value = false;
};

onMounted(() => {
  fetchNews();
});

const newsItems = computed(() => newsStore.newsItems);
</script>

<style scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center;
  color: #2c3e50;
}

.list-group-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.badge {
  font-size: 0.9em;
}
</style>

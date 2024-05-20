<template>
  <div id="news-detail" class="container mt-5">
    <h1 class="mb-4">뉴스 내용</h1>
    <div v-if="loading" class="alert alert-info">응답을 기다리는 중...</div>
    <div v-else-if="error" class="alert alert-danger">{{ error }}</div>
    <div v-else class="alert alert-success" v-html="newsContent"></div>
    <router-link to="/" class="btn btn-primary mt-3">돌아가기</router-link>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import { useRoute } from 'vue-router';
import { useNewsStore } from '@/util/news.js'

const newsStore = useNewsStore();
const route = useRoute();
const loading = ref(false);
const error = ref(null);

const fetchNewsContent = async () => {
  loading.value = true;
  error.value = null;

  const newsItem = newsStore.newsItems[route.params.id];
  console.log('newsItem:', newsItem);  // 디버깅을 위해 추가

  if (!newsItem) {
    error.value = '뉴스 항목을 찾을 수 없습니다.';
    loading.value = false;
    return;
  }

  try {
    await newsStore.fetchNewsContent(newsItem.link);
  } catch (err) {
    error.value = '뉴스 내용을 가져오는 중 오류가 발생했습니다.';
  } finally {
    loading.value = false;
  }
};

onMounted(() => {
  fetchNewsContent();
});

const newsContent = computed(() => newsStore.newsContent);
</script>

<style scoped>
#news-detail {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center;
  color: #2c3e50;
}

.mt-5 {
  margin-top: 3rem;
}
</style>

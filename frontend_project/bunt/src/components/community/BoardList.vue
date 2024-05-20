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
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { useNewsStore } from '@/util/news.js'

const route = useRoute();
const newsStore = useNewsStore();
const loading = ref(false);
const error = ref(null);
const newsContent = ref('');

const fetchNewsContent = async () => {
  loading.value = true;
  error.value = null;

  const newsLink = route.query.link;
  if (!newsLink) {
    error.value = '뉴스 링크를 찾을 수 없습니다.';
    loading.value = false;
    return;
  }

  try {
    await newsStore.fetchNewsContent(newsLink);
    newsContent.value = newsStore.newsContent;
  } catch (err) {
    error.value = '뉴스 내용을 가져오는 중 오류가 발생했습니다.';
  } finally {
    loading.value = false;
  }
};

onMounted(() => {
  fetchNewsContent();
});
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

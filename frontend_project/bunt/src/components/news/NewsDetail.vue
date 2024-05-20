<template>
  <div class="d-flex align-items-center justify-content-center min-vh-100">
    <div id="news-detail" class="container text-left mt-5">
      <h1 class="mb-4">뉴스 내용</h1>
      <div v-if="loading" class="alert alert-info">응답을 기다리는 중...</div>
      <div v-else-if="error" class="alert alert-danger">{{ error }}</div>
      <div v-else class="alert alert-success" v-html="newsContent"></div>
      <button @click="goBack" class="btn btn-secondary mb-3">돌아가기</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { useRouter } from 'vue-router'
import { useNewsStore } from '@/util/news.js'

const route = useRoute()
const router = useRouter()
const newsStore = useNewsStore()
const loading = ref(false)
const error = ref(null)
const newsContent = ref('')
// 이전 페이지로 이동 함수
const goBack = () => {
  router.back();
};

const fetchNewsContent = async () => {
  loading.value = true
  error.value = null

  const newsLink = route.query.link
  if (!newsLink) {
    error.value = '뉴스 링크를 찾을 수 없습니다.'
    loading.value = false
    return
  }

  try {
    await newsStore.fetchNewsContent(newsLink)
    newsContent.value = newsStore.newsContent
  } catch (err) {
    error.value = '뉴스 내용을 가져오는 중 오류가 발생했습니다.'
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  fetchNewsContent()
})
</script>

<style scoped>
@font-face {
  font-family: 'KBO Dia Gothic';
  src: url('@/assets/fonts/KBO Dia Gothic_medium.ttf') format('truetype');
}

#news-detail {
  font-family: 'KBO Dia Gothic', Avenir, Helvetica, Arial, sans-serif;
  color: #2c3e50;
}

h1, .alert {
  text-align: left;
}

.alert-success {
  white-space: pre-line; /* 개행 문자 처리를 위해 추가 */
}

.mt-5 {
  margin-top: 3rem;
}
</style>

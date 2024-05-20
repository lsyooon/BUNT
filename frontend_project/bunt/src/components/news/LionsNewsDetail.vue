<template>
  <div class="d-flex align-items-center justify-content-center min-vh-100 custom-bg">
    <div id="news-detail" class="container text-left mt-5 bg-white p-4 shadow rounded">
      <h1 class="mb-4">{{ newsTitle }}</h1>
      <div v-if="newsDate" class="mb-2 text-muted">{{ newsDate }}</div>
      <div v-if="loading" class="alert alert-info">응답을 기다리는 중...</div>
      <div v-else-if="error" class="alert alert-danger">{{ error }}</div>
      <div v-else class="alert custom-content-bg" v-html="newsContent"></div>
      <button @click="goBack" class="btn btn-secondary mb-3">돌아가기</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { useRouter } from 'vue-router'
import { useNewsStore } from '@/stores/lionsNews.js'

const route = useRoute()
const router = useRouter()
const newsStore = useNewsStore()
const loading = ref(false)
const error = ref(null)
const newsTitle = ref('')
const newsDate = ref('') // 뉴스 날짜를 저장할 상태 추가
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
    newsTitle.value = newsStore.newsTitle // 뉴스 제목 설정
    newsDate.value = newsStore.newsDate // 뉴스 날짜 설정
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
  max-width: 880px;
}

h1 {
  text-align: left;
  color: #0056b3;
}

.custom-content-bg {
  background-color: #e9ecef !important; /* 원하는 배경 색상으로 변경 */
  white-space: pre-line; /* 개행 문자 처리를 위해 추가 */
  padding: 1rem; /* 내용 배경 패딩 */
  border-radius: .3rem; /* 둥근 테두리 */
}

.custom-content-bg img {
  display: block;
  margin: 0 auto;
  max-width: 100%; /* 이미지가 컨테이너를 넘어가지 않도록 설정 */
  height: auto; /* 이미지 비율을 유지하며 높이를 자동으로 조절 */
}

.custom-bg {
  background-color: #f8f9fa; /* 전체 배경 색상 */
}

.mt-5 {
  margin-top: 3rem;
}

.bg-white {
  background-color: #ffffff !important;
}

.p-4 {
  padding: 1.5rem !important;
}

.shadow {
  box-shadow: 0 .5rem 1rem rgba(0, 0, 0, .15) !important;
}

.rounded {
  border-radius: .3rem !important;
}

.btn-secondary {
  background-color: #6c757d !important;
  border-color: #6c757d !important;
}
</style>

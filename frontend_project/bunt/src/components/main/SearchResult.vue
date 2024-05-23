<template>
  <div class="video-result">
    <h4 class="mb-4">검색결과</h4>
    <ul class="list-group">
      <li class="list-group-item d-flex align-items-center video-item" @click="clickVideo">
        <img v-if="video.snippet" :src="video.snippet.thumbnails.default.url" alt="Video thumbnail" class="thumbnail">
        <span v-if="video.snippet" class="video-title ml-3">{{ video.snippet.title }}</span>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { useYoutubeStore } from '@/stores/youtube';

const store = useYoutubeStore();

const props = defineProps({
  video: {
    type: Object,
    required: true
  }
});

const clickVideo = () => {
  store.clickVideo(props.video);
};
</script>

<style scoped>
.video-result {
  padding-top: 5%;
}

.list-group-item {
  background-color: #e0e7ff !important; /* 부드러운 파스텔 블루 */
  color: #4c4c4c !important; /* 텍스트 색상 */
  border-color: #e0e7ff !important; /* 테두리 색상 */
  transition: background-color 0.3s ease;
  cursor: pointer;
}

.list-group-item:hover {
  background-color: #c3dafe !important; /* 호버 상태에서 조금 더 어두운 파스텔 블루 */
}

.video-item {
  display: flex;
  align-items: center;
}

.thumbnail {
  width: 60px;
  height: auto;
}

.video-title {
  margin-left: 10px;
  color: #4c4c4c !important; /* 텍스트 색상 */
}

.video-title:hover {
  color: #1c7ed6 !important; /* 링크 호버 색상을 밝은 블루로 변경 */
}
</style>

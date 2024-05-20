<template>
  <div>
    <h4>검색결과</h4>
    <ul>
      <li @click="clickVideo">
        <img v-if="video.snippet" :src="video.snippet.thumbnails.default.url" alt="Video thumbnail">
        <span v-if="video.snippet">{{ video.snippet.title }}</span>
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

<style scoped></style>

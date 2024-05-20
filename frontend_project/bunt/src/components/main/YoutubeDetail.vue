<template>
  <div v-if="store.selectedVideo">
    <iframe
        width="560"
        height="315"
        :src="videoURL"
        title="YouTube video player"
        frameborder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
        referrerpolicy="strict-origin-when-cross-origin"
        allowfullscreen
    ></iframe>
  </div>
</template>

<script setup>
import { useYoutubeStore } from '@/stores/youtube';
import { computed } from 'vue';

const store = useYoutubeStore();

const videoURL = computed(() => {
  if (store.selectedVideo && store.selectedVideo.id) {
    return `https://www.youtube.com/embed/${store.selectedVideo.id.videoId}`;
  }
  return '';
});
</script>

<style scoped></style>

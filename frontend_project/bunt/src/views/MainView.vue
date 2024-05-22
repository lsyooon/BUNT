<template>
  <div class="container">
    <RouterLink :to="{ name: 'main', params: { teamId: '1' } }" class="logo">
      <img src="@/assets/image_logo/LOTTE_logo.svg" alt="LOTTE Logo" class="animated-logo">
    </RouterLink>
    <RouterLink :to="{ name: 'main', params: { teamId: '2' } }" class="logo">
      <img src="@/assets/image_logo/SAMSUNG_logo.svg" alt="SAMSUNG Logo" class="animated-logo">
    </RouterLink>
    <RouterLink :to="{ name: 'main', params: { teamId: '3' } }" class="logo">
      <img src="@/assets/image_logo/KIA_LOGO.svg" alt="KIA Logo" class="animated-logo">
    </RouterLink>
    <RouterLink :to="{ name: 'main', params: { teamId: '4' } }" class="logo">
      <img src="@/assets/image_logo/DOOSAN_LOGO.svg" alt="DOOSAN Logo" class="animated-logo">
    </RouterLink>
    <RouterLink :to="{ name: 'main', params: { teamId: '5' } }" class="logo">
      <img src="@/assets/image_logo/EAGLES_logo.svg" alt="EAGLES Logo" class="animated-logo">
    </RouterLink>
  </div>
</template>

<script setup>
</script>

<style scoped>
html, body {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  overflow-x: hidden; /* 가로 스크롤을 숨김 */
}

.container {
  display: flex;
  justify-content: center; /* 중앙 정렬 */
  align-items: center; /* 세로 중앙 정렬 */
  height: 100vh; /* 화면 전체 높이 */
  width: 100vw; /* 화면 전체 너비 */
  background-image: url('@/assets/image_background/LOTTE_HOME.webp');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}

.logo {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}

.animated-logo {
  max-width: 100%;
  height: auto;
  opacity: 1;
  transform: scale(1);
  animation: entranceAnimation 1.5s forwards, floatAnimation 3s ease-in-out infinite;
}

@keyframes entranceAnimation {
  to {
    opacity: 1;
    transform: scale(1) rotate(0deg);
  }
}

@keyframes floatAnimation {
  0%, 100% {
    transform: translateY(0);
  }
  50% {
    transform: translateY(-20px);
  }
}

.animated-logo:hover {
  animation: floatAnimationPaused 0s forwards;
  transform: scale(0.9);
  transition: transform 0.5s;
}

@keyframes floatAnimationPaused {
  0% {
    transform: translateY(0);
  }
}

</style>

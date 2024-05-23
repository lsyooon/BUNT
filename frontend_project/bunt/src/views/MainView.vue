<template>
  <div class="cont">
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
/* CSS 리셋 */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html, body {
  width: 100%;
  height: 100%;
  overflow-x: hidden; /* 가로 스크롤을 숨김 */
}

.cont {
  display: flex;
  justify-content: space-between; /* 각 로고 사이에 여백을 없앱니다 */
  align-items: stretch; /* 로고가 전체 높이를 차지하도록 합니다 */
  height: 100vh; /* 화면 전체 높이 */
  width: 100vw; /* 화면 전체 너비 */
  background-image: url('@/assets/image_background/bg.png');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  margin: 0;
}

.logo {
  flex: 1; /* 모든 로고가 균등하게 너비를 차지하도록 합니다 */
  display: flex;
  justify-content: center;
  align-items: center;
}



.animated-logo {
  width: 100%;
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

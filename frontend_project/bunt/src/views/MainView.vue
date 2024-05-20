<template>
  <div class="container">
    <RouterLink to="/main/samsung/1" class="logo">
      <img src="@/assets/image_logo/SAMSUNG_logo.png" alt="SAMSUNG Logo">
    </RouterLink>
    <RouterLink to="/main/lotte/2" class="logo">
      <img src="@/assets/image_logo/LOTTE_logo.png" alt="LOTTE Logo">
    </RouterLink>
  </div>
</template>

<script setup>
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center; /* 중앙 정렬 */
  align-items: center; /* 세로 중앙 정렬 */
  height: 100vh; /* 화면 전체 높이 */
}

.logo {
  flex: 1; /* 각 로고가 flex 컨테이너의 반씩 차지 */
  display: flex;
  justify-content: center; /* 로고를 중앙으로 정렬 */
  align-items: center; /* 로고를 세로 중앙으로 정렬 */
}

.logo img {
  max-width: 90%; /* 로고 이미지의 최대 너비를 90%로 설정 */
  height: auto; /* 이미지 비율 유지 */
  transition: transform 0.3s; /* 마우스 오버 시 부드러운 애니메이션 */
}

.logo img:hover {
  transform: scale(1.05); /* 마우스 오버 시 이미지 확대 */
}
</style>

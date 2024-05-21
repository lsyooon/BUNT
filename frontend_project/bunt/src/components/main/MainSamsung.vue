<!-- MainSamsung.vue -->
<template>
  <div class="cont">
    <div class="row">
      <div class="main-top">
        <div class="main-left">
          <img src="@/assets/image_logo/SAMSUNG_logo.svg" class="background-img">
          <div class="buttons">
            <button v-for="player in players" :key="player.playerId" @click="searchPlayer(player)">
              <p>{{ player.position }}　　　{{ player.name }}　　　{{ player.backNumber }}</p>
            </button>
          </div>
        </div>
        <div class="main-center">
        </div>
        <div class="main-right">
          <VideoDetail v-if="youtubeStore.selectedVideo" :video="youtubeStore.selectedVideo" />
        </div>
      </div>
      <team-rank></team-rank>
    </div>
  </div>
</template>

<style scoped>
.cont {
  margin-top: 30px;
}

.row {
  margin: 0;
  background-image: url('@/assets/image_background/SAMSUNG_BG.jpeg'); /* 배경 이미지 경로를 삼성에 맞게 수정 */
  background-size: cover; /* 추가 */
  background-position: center; /* 추가 */
  background-repeat: no-repeat; /* 추가 */
}

.main-top {
  display: flex;
  margin-bottom: 0.5%;
}

.main-left {
  flex: 4;
  position: relative;
  display: flex;
  flex-direction: column;
  padding-right: 3%;
  padding-left: 1%;
}

.background-img {
  width: 100%;
  height: 100%; /* 변경 */
  position: absolute;
  top: 0;
  left: 0;
  z-index: -1;
  opacity: 0.5; /* 필요에 따라 투명도 조절 */
}

.main-left button {
  transform: skewX(-45deg);
  display: block;
  width: 300px;
  height: 30px;
  text-align: center;
  margin-bottom: 5.5px;
  line-height: 40px;
  border: none;
  background-color: lightgray;
  opacity: 0.8;
}

.main-left button:hover {
  opacity: 1;
  background-color: gray;
}

.main-left button p {
  transform: skewX(45deg);
}

.main-center {
  flex: 4;
  position: relative; /* for background image positioning */
}

.main-right {
  flex: 4;
  position: relative; /* for background image positioning */
}

.buttons {
  margin-top: 5%;
  margin-bottom: 5%;
  flex: 2;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}
</style>

<script setup lang="ts">
import { ref, onMounted, watch } from 'vue';
import { usePlayerStore } from '@/stores/player.js';
import { useYoutubeStore } from '@/stores/youtube.js';
import TeamRank from '@/components/main/teamRank.vue';
import VideoDetail from '@/components/main/YoutubeDetail.vue';

const playerStore = usePlayerStore();
const youtubeStore = useYoutubeStore();
const keyword = ref('');

const search = () => {
  youtubeStore.youtubeSearch(keyword.value);
};

// 페이지 처음 들어왔을 때
onMounted(() => {
  // 초기 검색어 설정
  keyword.value = '삼성라이온즈 치고 달려라 응원가';
  // 초기 검색 실행
  search();
  // 팀 ID를 사용하여 선수 목록 로드
  const teamId = 2; // 예시로 팀 ID 2 사용
  playerStore.findPlayersByTeamId(teamId).then(() => {
    console.log('플레이어 목록 로드 완료:', playerStore.players);
  });
});

// players 데이터가 변경될 때 콘솔 로그 추가
watch(() => playerStore.players, (newPlayers) => {
  console.log('플레이어 데이터 변경:', newPlayers);
});

const searchPlayer = (player) => {
  keyword.value = `삼성라이온즈 ${player.name} 응원가`;
  search();
  playerStore.findPlayer(player);
};

const { players } = playerStore;
</script>

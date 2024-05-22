<template>
  <div class="cont">
    <div class="row">
      <div class="main-top">
        <div class="main-left">
          <div class="button-columns">
            <div class="button-column" v-for="(column, columnIndex) in buttonColumns" :key="columnIndex">
              <button v-for="player in column" :key="player.playerId" @click="searchPlayer(player)" style="width: 250px">
                <p>{{ player.position }}　　　{{ player.name }}　　　{{ player.backNumber }}</p>
              </button>
            </div>
          </div>
        </div>
        <div class="main-center">
          <VideoDetail v-if="youtubeStore.selectedVideo" :video="youtubeStore.selectedVideo" />
        </div>
        <div class="main-right">
        </div>
      </div>
      <team-rank></team-rank>
    </div>
  </div>
</template>

<style scoped>
.cont {
  padding-top: 50px;
  margin-top: 30px;
}

.row {
  margin: 0;
  background-image: url('@/assets/image_background/LOTTE_BG7.jpeg');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
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

.button-columns {
  display: flex;
}

.button-column {
  display: flex;
  flex-direction: column;
  margin-right: 10px;
}

.main-left button {
  transform: skewX(-45deg);
  display: block;
  width: 300px;
  height: 30px;
  text-align: center;
  margin-bottom: 6px;
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
  display: flex;
  justify-content: center;
  align-items: center;
}

.main-right {
  flex: 4;
  position: relative;
}

.button-column {
  margin-top: 5%;
  margin-bottom: 5%;
  flex: 2;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
</style>

<script setup lang="ts">
import { ref, onMounted, watch, computed } from 'vue';
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
  keyword.value = '롯데자이언츠 부산 갈매기 응원가';
  // 초기 검색 실행
  search();
  // 팀 ID를 사용하여 선수 목록 로드
  const teamId = 1; // 예시로 팀 ID 1 사용
  playerStore.findPlayersByTeamId(teamId).then(() => {
    console.log('플레이어 목록 로드 완료:', playerStore.players);
  });
});

// players 데이터가 변경될 때 콘솔 로그 추가
watch(() => playerStore.players, (newPlayers) => {
  console.log('플레이어 데이터 변경:', newPlayers);
});

const searchPlayer = (player) => {
  keyword.value = `롯데자이언츠 ${player.name} 응원가`;
  search();
  playerStore.findPlayer(player);
};

// players를 10개 단위로 나누어 buttonColumns로 설정
const buttonColumns = computed(() => {
  const columns = [];
  for (let i = 0; i < playerStore.players.length; i += 10) {
    columns.push(playerStore.players.slice(i, i + 10));
  }
  return columns;
});

const { players } = playerStore;
</script>

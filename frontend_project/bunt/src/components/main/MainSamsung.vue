<template>
  <div class="cont">
    <div class="row">
      <div class="main-left">
        <div class="top-section">
          <div class="button-columns">
            <div class="button-column" v-for="(column, columnIndex) in buttonColumns" :key="columnIndex">
              <button v-for="player in column" :key="player.playerId" @click="searchPlayer(player)" style="width: 250px">
                <p>{{ player.position }}　　　{{ player.name }}　　　{{ player.backNumber }}</p>
              </button>
            </div>
          </div>
          <div class="video">
            <VideoDetail v-if="youtubeStore.selectedVideo" :video="youtubeStore.selectedVideo" />
          </div>
        </div>
        <div class="bottom-section">
          <team-rank></team-rank>
        </div>
      </div>
      <div class="main-right">
        <iframe class="chat"
                :src="iframeSrc"
                frameborder='no' scrolling='no' marginwidth='0' marginheight='0' width='100%' height='100%'></iframe>
      </div>
    </div>
  </div>
</template>

<style scoped>
.cont {
  padding-top: 50px;
  margin-top: 30px;
}

.row {
  padding-top: 3%;
  display: flex;
  width: 100%;
  background-image: url('@/assets/image_background/SS_BG.png');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}

.main-left {
  flex: 2;
  display: flex;
  flex-direction: column;
  padding-right: 1%;
  padding-left: 1%;
}

.top-section {
  display: flex;
}

.button-columns {
  display: flex;
  flex: 1;
  margin-right: 20px;
}

.button-column {
  display: flex;
  flex-direction: column;
  margin-left: 20px;
}

.video {
  flex: 2;
  padding-top: 1%;
}

.main-left button {
  transform: skewX(-45deg);
  display: block;
  width: 250px;
  height: 30px;
  text-align: center;
  margin-bottom: 6px;
  line-height: 30px;
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

.bottom-section {
  margin-top: 20px;
}

.main-right {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0;
}

.chat {
  margin-right: 1%;
  width: 100%;
  height: 100%;
  border: none;
}
</style>

<script setup lang="js">
import { ref, onMounted, watch, computed } from 'vue'
import { usePlayerStore } from '@/stores/player.js'
import { useYoutubeStore } from '@/stores/youtube.js'
import TeamRank from '@/components/main/teamRank.vue'
import VideoDetail from '@/components/main/YoutubeDetail.vue'

const playerStore = usePlayerStore()
const youtubeStore = useYoutubeStore()
const keyword = ref('')

const iframeSrc = `https://www.vchatcloud.com/chat-demo/iframe/iframe_pc/v4/index.html?channelKey=${import.meta.env.VITE_VCHAT_API_KEY}`

const search = () => {
  youtubeStore.youtubeSearch(keyword.value)
}

// 페이지 처음 들어왔을 때
onMounted(() => {
  // 초기 검색어 설정
  keyword.value = '삼성라이온즈 구단 응원가'
  // 초기 검색 실행
  search()
  // 팀 ID를 사용하여 선수 목록 로드
  const teamId = 2
  playerStore.findPlayersByTeamId(teamId).then(() => {
    console.log('플레이어 목록 로드 완료:', playerStore.players)
  })
})

// players 데이터가 변경될 때 콘솔 로그 추가
watch(() => playerStore.players, (newPlayers) => {
  console.log('플레이어 데이터 변경:', newPlayers)
})

const searchPlayer = (player) => {
  keyword.value = `삼성라이온즈 ${player.name} 응원가`
  search()
  playerStore.findPlayer(player)
}

// players를 10개 단위로 나누어 buttonColumns로 설정
const buttonColumns = computed(() => {
  const columns = []
  for (let i = 0; i < playerStore.players.length; i += 10) {
    columns.push(playerStore.players.slice(i, i + 10))
  }
  return columns
})

const { players } = playerStore
</script>

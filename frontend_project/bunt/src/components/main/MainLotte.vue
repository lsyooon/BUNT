<template>
  <div>
    <div class="row">
      <div class="background-picture col-10">
        <div>
          <p>사진</p>
        </div>
      </div>
      <div class="game col-2">
        <div class="best-player">
          <h4>이달의 선수</h4>
          <img style="width: 150px" src="@/assets/images/goo.jpg" alt="">
          <h4>다음 경기 일정</h4>
        </div>
        <div class="next-play"></div>
        <div class="recent-play-title">
          <h4>최근 경기 결과</h4>
        </div>
        <div class="recent-play">
        </div>
      </div>
      <team-rank></team-rank>
    </div>
  </div>
</template>

<style scoped>
.row {
  margin: 0;
}

.background-picture {
  background-image: url("@/assets/images/image.png");
  background-repeat: no-repeat;
  background-size: cover;
  height: 100vh;
  box-sizing: border-box;
}

.game {
  height: 100vh;
  box-sizing: border-box;
  padding: 0;
  border-width: 0 1px 0px 0px;
  border-style: solid;
}

.best-player {
  background-color: #455d9f;
  height: 44%;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
}

.next-play {
  height: 8%;
}

.recent-play-title {
  background-color: #455d9f;
  height: 8%;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
}

.recent-play {
  display: flex;
  flex-direction: column;
  height: 40%;
}

.recent-play div {
  list-style: none;
  border-bottom: 1px solid;
  height: 20%;
}

h4 {
  color: white;
}

</style>
<script setup lang="ts">
import TeamRank from "@/components/main/teamRank.vue";
</script>
<!-- TeamRank.vue -->
<template>
  <div class="container">
    <table class="table table-bordered table-hover rounded-table" v-if="teams.length">
      <thead class="thead-dark">
      <tr>
        <th scope="col">순위</th>
        <th scope="col">팀</th>
        <th scope="col">경기수</th>
        <th scope="col">승</th>
        <th scope="col">패</th>
        <th scope="col">무</th>
        <th scope="col">승률</th>
        <th scope="col">게임차</th>
        <th scope="col">연속</th>
        <th scope="col">출루율</th>
        <th scope="col">장타율</th>
        <th scope="col">최근 10경기</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="team in teams" :key="team.rank">
        <th scope="row">{{ team.rank }}</th>
        <td>{{ team.name }}</td>
        <td>{{ team.gamesPlayed }}</td>
        <td>{{ team.wins }}</td>
        <td>{{ team.losses }}</td>
        <td>{{ team.draws }}</td>
        <td>{{ team.winRate }}</td>
        <td>{{ team.gamesBehind }}</td>
        <td>{{ team.recentRecord }}</td>
        <td>{{ team.battingAvg }}</td>
        <td>{{ team.onBasePct }}</td>
        <td>{{ team.last10Games }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import teamData from '@/util/teamData.json';

export default {
  data() {
    return {
      teams: []
    };
  },
  created() {
    this.teams = teamData;
  }
};
</script>

<style>
body {
  background-color: #f8f9fa;
}

h1 {
  text-align: center;
  color: #333;
  font-weight: bold;
}

.table {
  background-color: rgba(255, 255, 255, 0.3); /* 투명도 추가 */
  opacity: 0.7; /* 추가 투명도 설정 */
}

.rounded-table {
  border-radius: 15px;
  overflow: hidden;
}

thead.thead-dark th {
  background-color: rgba(69, 93, 159, 0.3); /* 투명도 추가 */
  border: none;
  opacity: 0.7; /* 추가 투명도 설정 */
}

thead.thead-dark th,
tbody tr {
  color: #fff;
}

tbody tr:nth-child(odd) {
  background-color: rgba(108, 117, 125, 0.3); /* 투명도 추가 */
  opacity: 0.7; /* 추가 투명도 설정 */
}

tbody tr:nth-child(even) {
  background-color: rgba(90, 98, 104, 0.3); /* 투명도 추가 */
  opacity: 0.7; /* 추가 투명도 설정 */
}

tbody tr:hover {
  background-color: rgba(73, 80, 87, 0.3); /* 투명도 추가 */
  opacity: 0.7; /* 추가 투명도 설정 */
}

th, td {
  text-align: center;
  vertical-align: middle;
}
</style>

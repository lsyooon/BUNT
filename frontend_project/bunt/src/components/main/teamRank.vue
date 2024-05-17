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
  margin-top: 20px;
  background-color: #fff;
}

.rounded-table {
  border-radius: 15px;
  overflow: hidden;
}

thead.thead-dark th {
  background-color: #455d9f;
  border: none;
}

thead.thead-dark th,
tbody tr {
  color: #fff;
}

tbody tr:nth-child(odd) {
  background-color: #6c757d;
}

tbody tr:nth-child(even) {
  background-color: #5a6268;
}

tbody tr:hover {
  background-color: #495057;
}

th, td {
  text-align: center;
  vertical-align: middle;
}
</style>

<template>
  <div id="cont">
    <h1>숫자 야구 게임</h1>
    <div v-if="!nickname">
      <input v-model="tempNickname" type="text" placeholder="닉네임을 입력하세요"/>
      <button @click="startGame">게임 시작</button>
    </div>
    <div v-else>
      <h5>환영합니다, {{ nickname }}님!</h5>

      <button @click="modalOpen"><img src="@/assets/image_icons/rank.png" style="width: 25px; margin-right: 2px">
        <span style="font-size: 20px">랭킹</span><img src="@/assets/image_icons/rank.png" style="width: 25px; margin-left: 2px"></button>
      <div class="modal-wrap" v-show="modalCheck">
        <div class="modal-container">
          <div v-if="ranking.length">
            <h2>랭킹</h2>
            <table>
              <thead>
              <tr>
                <th>순위</th>
                <th>닉네임</th>
                <th>점수</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(rank, index) in sortedRanking" :key="index">
                <td>{{ index + 1 }}</td>
                <td>{{ rank.nickname }}</td>
                <td>{{ rank.score }}</td>
              </tr>
              </tbody>
            </table>
          </div>
          <div class="modal-btn">
            <button @click="modalOpen">닫기</button>
          </div>
        </div>
      </div>
      <div>
        <input v-model="userInput" type="text" placeholder="숫자를 입력하세요 (예: 1234)" @keyup.enter="checkNumber"/>
        <button @click="checkNumber"><img class="img-button" src="@/assets/image_icons/game-button.png" alt=""></button>
      </div>
      <div v-if="message">
        <p>{{ message }}</p>
      </div>
      <div v-if="results.length">
        <table>
          <thead>
          <tr>
            <th>시도 횟수</th>
            <th>입력한 숫자</th>
            <th>결과</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(result, index) in results" :key="index">
            <td>{{ index + 1 }}</td>
            <td>{{ result.userNumber }}</td>
            <td>{{ result.result }}</td>
          </tr>
          </tbody>
        </table>
      </div>
      <div v-if="gameOver">
        <button @click="resetGame"><img src="@/assets/image_icons/restart.png" style="width: 25px">다시하기</button>
      </div>
    </div>


  </div>
</template>

<script setup>
import {ref, onMounted, computed} from 'vue';

// 모달
const modalCheck = ref(false);

const modalOpen = () => {
  modalCheck.value = !modalCheck.value;
};

const createRandomNumber = () => {
  let nums = [];
  while (nums.length < 4) {
    let num = Math.floor(Math.random() * 9) + 1;
    if (!nums.includes(num)) {
      nums.push(num);
      console.log(num);
    }
  }
  return nums.join('');
};

const calculateScore = (attempts) => {
  return Math.max(0, 100 - (attempts - 1) * 10);
};

const randomNumber = ref('');
const tempNickname = ref('');
const nickname = ref('');
const userInput = ref('');
const results = ref([]);
const message = ref('');
const ranking = ref([]);
const gameOver = ref(false);

const saveRanking = () => {
  localStorage.setItem('ranking', JSON.stringify(ranking.value));
  localStorage.setItem('lastUpdated', new Date().toISOString().split('T')[0]);
};

const loadRanking = () => {
  const storedRanking = localStorage.getItem('ranking');
  const lastUpdated = localStorage.getItem('lastUpdated');
  const today = new Date().toISOString().split('T')[0];

  if (lastUpdated !== today) {
    ranking.value = [];
    localStorage.removeItem('ranking');
    localStorage.setItem('lastUpdated', today);
  } else if (storedRanking) {
    ranking.value = JSON.parse(storedRanking);
  }
};

const startGame = () => {
  if (tempNickname.value.trim() !== '') {
    nickname.value = tempNickname.value.trim();
    resetGame();
  } else {
    message.value = '닉네임을 입력하세요.';
  }
};

const checkNumber = () => {
  if (results.value.length >= 10) {
    message.value = '실패했습니다! 10번의 시도 동안 정답을 맞추지 못했습니다.';
    gameOver.value = true;
    return;
  }

  const userNumber = userInput.value;
  if (userNumber.length !== 4 || isNaN(userNumber)) {
    message.value = '네 자리 숫자를 입력하세요.';
    return;
  }

  const userNumberArray = userNumber.split('');

  if (new Set(userNumberArray).size !== userNumberArray.length) {
    message.value = '중복되지 않은 숫자를 입력하세요.';
    return;
  }

  const randomArray = randomNumber.value.split('');

  let strikes = 0;
  let balls = 0;

  userNumberArray.forEach((num, index) => {
    if (num === randomArray[index]) {
      strikes++;
    }
  });

  userNumberArray.forEach((num, index) => {
    if (num !== randomArray[index] && randomArray.includes(num)) {
      balls++;
    }
  });

  results.value.push({userNumber, result: `${strikes} 스트라이크, ${balls} 볼`});

  // const index = ref(1);
  // const cnt = ref(1);
  // const rankArr = ref([]);
  // const calculateIndex = async() => {
  //   const sorted = ref([])
  //   sorted.value = sortedRanking2();
  //   console.log(sorted)
  //   for(let i = 0; i < sorted.value.length; i++) {
  //     // sorted.value[i] = sorted.value[i].userNumber;
  //     if(sorted.value[i] === sorted.value[i+1]){
  //       cnt.value += 1;
  //     }
  //     else {
  //       index.value += cnt.value;
  //       cnt.value = 1;
  //     }
  //     rankArr.value[i] = index.value;
  //   }
  //   console.log(sorted)
  // }

  if (strikes === 4) {
    const score = calculateScore(results.value.length);
    // const arr = calculateIndex();
    ranking.value.push({nickname: nickname.value, score});
    saveRanking();
    message.value = `와아아아! 정답입니다! ${nickname.value}님의 점수는 ${score}점입니다.`;
    gameOver.value = true;
  } else {
    message.value = `${strikes} 스트라이크, ${balls} 볼`;
  }
  userInput.value = '';
};

const resetGame = () => {
  randomNumber.value = createRandomNumber();
  userInput.value = '';
  results.value = [];
  message.value = '';
  gameOver.value = false;
};

const sortRankingByScore = () => {
  ranking.value.sort((a, b) => b.score - a.score);
};

// 템플릿에서 사용할 ranking 배열을 정렬된 상태로 가져옴
const sortedRanking = computed(() => {
  sortRankingByScore();
  return ranking.value;
});

// const sortedRanking2 = async () => {
//   sortRankingByScore();
//   return ranking;
// };

onMounted(() => {
  loadRanking();
  resetGame();
});

</script>

<style>
#cont {
  text-align: center;
  margin-top: 4%;
}

input {
  width: 20%;
  margin-top: 2%;
  padding: 5px;
  margin-right: 5px;
}

button {
  border: none;
  background-color: transparent;
}

ul {
  list-style-type: none;
  padding: 0;
}

.img-button {
  width: 60px;
  height: 60px;
  transition: transform 0.2s;
}

.img-button:hover {
  transform: scale(1.2);
}

table {
  width: 50%;
  margin: 0 auto;
  border-collapse: collapse;
  margin-top: 1%;
}

th, td {
  border: 1px solid #ddd;
  padding: 8px;
  color: #333;
}

th {
  background-color: #f2f2f2;
}

td {
  text-align: center;
}

.modal-wrap {
  position: fixed;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.4);
  z-index: 1000;
}

.modal-container {
  position: relative;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 550px;
  background: #fff;
  border-radius: 10px;
  padding: 20px;
  box-sizing: border-box;
}
</style>
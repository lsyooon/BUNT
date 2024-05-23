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
          <div v-if="rankData.length">
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
              <tr v-for="(rank) in rankData">
                <td>{{ rank.rank}}</td>
                <td>{{ rank.nickName }}</td>
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
import { ref, onMounted } from 'vue';
import axios from "axios";

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
const gameOver = ref(false);

const saveGameData = async () => {
  try {
    const response = await axios.post('http://localhost:8080/api/game', {
      nickName: nickname.value,
      score: calculateScore(results.value.length)
      // 다른 필요한 데이터 추가
    });
    console.log('게임 데이터 저장 성공:', response.data)
    getRankData()
    modalOpen()
  } catch (error) {
    console.error('게임 데이터 저장 실패:', error);
  }
};

const rankData = ref([])
const getRankData = () => { // 데이터를 가져오는 함수 정의
  axios.get('http://localhost:8080/api/game')
      .then(response => {
        rankData.value = response.data;
        console.log('게임 데이터 불러오기 성공:', response.data);
      })
      .catch(error => {
        console.error('게임 데이터 불러오기 실패:', error);
      });
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

  if (strikes === 4) {
    saveGameData(); // 게임이 끝나면 데이터를 저장합니다.
    message.value = `와아아아! 정답입니다! ${nickname.value}님의 점수는 ${calculateScore(results.value.length)}점입니다.`;
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

onMounted(() => {
  getRankData();
  resetGame();
});

</script>

<style>
#cont {
  text-align: center;
  padding-top: 5%;
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
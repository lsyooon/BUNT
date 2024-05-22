<template>
  <div class="d-flex align-items-center justify-content-center min-vh-100">
    <div id="app" class="container d-flex flex-column flex-lg-row text-center mt-5 animate__animated animate__zoomIn">
      <div class="keyword-container flex-fill p-3">
        <div class="button-container d-flex flex-wrap justify-content-center mb-4">
          <button @click="sendMessage('KBO의 롯데 자이언츠의 우승 확률은? 현재 10등이야.')" class="btn btn-outline-primary m-2 shadow">롯데 자이언츠의 우승 확률</button>
          <button @click="sendMessage('KBO의 삼성 라이온즈의 우승 확률은? 현재 3등이야.')" class="btn btn-outline-primary m-2 shadow">삼성 라이온즈의 우승 확률</button>
          <button @click="sendMessage('KBO의 기아 타이거즈의 우승 확률은? 현재 3등이야.')" class="btn btn-outline-primary m-2 shadow">기아 타이거즈의 우승 확률</button>
          <button @click="sendMessage('KBO의 두산 베어스의 우승 확률은? 현재 3등이야.')" class="btn btn-outline-primary m-2 shadow">두산 베어스의 우승 확률</button>
          <button @click="sendMessage('KBO의 한화 이글스의 우승 확률은? 현재 3등이야.')" class="btn btn-outline-primary m-2 shadow">한화 이글스의 우승 확률</button>
          <button @click="sendMessage('한국에서 야구장 먹거리가 가장 맛있는 곳은 어디야')" class="btn btn-outline-warning m-2 shadow">야구장 맛집 1등은?</button>
          <button @click="sendMessage('대한민국 프로야구인 KBO에서 응원을 가장 잘하는 팀은?')" class="btn btn-outline-warning m-2 shadow">야구장 응원 1등팀은?</button>
          <button @click="sendMessage('대한민국 프로야구인 KBO에서 가장 인기있는 야구 팀은?')" class="btn btn-outline-warning m-2 shadow">가장 인기있는 야구팀은?</button>
          <button @click="sendMessage('야구에서 아웃이 되는 경우')" class="btn btn-outline-success m-2 shadow">아웃이 되는 경우</button>
          <button @click="sendMessage('야구의 이닝 구성에 대해 설명해줘')" class="btn btn-outline-success m-2 shadow">이닝 구성</button>
          <button @click="sendMessage('야구에서 투수의 역할')" class="btn btn-outline-success m-2 shadow">투수의 역할</button>
          <button @click="sendMessage('야구에서 타자의 역할')" class="btn btn-outline-success m-2 shadow">타자의 역할</button>
          <button @click="sendMessage('야구에서 수비 포지션')" class="btn btn-outline-success m-2 shadow">수비 포지션</button>
          <button @click="sendMessage('야구에서 홈플레이트와 베이스')" class="btn btn-outline-success m-2 shadow">홈플레이트와 베이스</button>
          <button @click="sendMessage('야구에서 삼진이란?')" class="btn btn-outline-success m-2 shadow">삼진</button>
          <button @click="sendMessage('야구에서 볼넷이란?')" class="btn btn-outline-success m-2 shadow">볼넷</button>
          <button @click="sendMessage('야구에서 보크이란?')" class="btn btn-outline-success m-2 shadow">보크</button>
          <button @click="sendMessage('야구에서 피쳐클락이란?')" class="btn btn-outline-success m-2 shadow">피쳐클락</button>
          <button @click="sendMessage('야구에서 도루란?')" class="btn btn-outline-success m-2 shadow">도루</button>
          <button @click="sendMessage('야구에서 더블 플레이란?')" class="btn btn-outline-success m-2 shadow">더블 플레이</button>
          <button @click="sendMessage('야구에서 희생 플라이란?')" class="btn btn-outline-success m-2 shadow">희생 플라이</button>
          <button @click="sendMessage('야구에서 파울볼이란?')" class="btn btn-outline-success m-2 shadow">파울볼</button>
          <button @click="sendMessage('야구에서 번트란?')" class="btn btn-outline-success m-2 shadow">번트</button>
          <button @click="sendMessage('야구에서 사이클링 히트란?')" class="btn btn-outline-info m-2 shadow">사이클링 히트</button>
          <button @click="sendMessage('야구에서 노히트 노런이란?')" class="btn btn-outline-info m-2 shadow">노히트 노런</button>
          <button @click="sendMessage('야구에서 퍼펙트 게임이란?')" class="btn btn-outline-info m-2 shadow">퍼펙트 게임</button>
          <button @click="sendMessage('야구에서 슬러거란?')" class="btn btn-outline-info m-2 shadow">슬러거</button>
          <button @click="sendMessage('야구에서 클러치 히터란?')" class="btn btn-outline-info m-2 shadow">클러치 히터</button>
        </div>
      </div>
      <div class="response-container flex-fill p-3">
        <div v-if="loading" class="alert alert-info w-100 animate__animated animate__flash">응답을 기다리는 중...</div>
        <div v-else class="alert alert-success w-100 response-box animate__animated animate__fadeIn">{{ response }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { GoogleGenerativeAI } from '@google/generative-ai';

const apiKey = import.meta.env.VITE_GENERATIVE_AI_API_KEY;

export default {
  name: 'App',
  data() {
    return {
      userInput: '',
      response: '',
      loading: false,
    };
  },
  methods: {
    async sendMessage(searchTerm) {
      if (!apiKey) {
        console.error('API 키를 다시 확인하세요.');
        return;
      }

      const genAI = new GoogleGenerativeAI(apiKey);
      this.loading = true;
      this.response = '';

      try {
        const model = genAI.getGenerativeModel({ model: 'gemini-pro' });

        const chat = model.startChat({
          history: [
            {
              role: 'user',
              parts: [{ text: '안녕 난 야구 규칙에 대해서 알고 싶어.' }],
            },
            {
              role: 'model',
              parts: [{ text: '야구 규칙에 대해서 설명해줄게' }],
            },
          ],
          generationConfig: {
            maxOutputTokens: 100,
          },
        });

        // 사용자 입력 또는 버튼 클릭 시 전달된 검색어에 "30자 이내로" 추가
        const query = `${searchTerm} 사전 정의식으로 답변 부탁하고, 강조표시는 생략해서 30자 이내로 답변해줘`;

        const result = await chat.sendMessageStream(query);

        for await (const item of result.stream) {
          this.response += item.candidates[0]?.content?.parts[0]?.text;
        }
      } catch (error) {
        console.error('Error:', error);
        this.response = '응답을 가져오는 중 오류가 발생했습니다.';
      } finally {
        this.loading = false;
      }
    },
  },
};
</script>

<style scoped>
@import 'https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css';

#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  max-width: 1200px;
  padding: 2rem;
  background-color: white;
  border-radius: 0.5rem;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.keyword-container, .response-container {
  flex: 1;
  min-height: 50px; /* 최소 높이를 지정하여 빈 공간 확보 */
  min-width: 400px;
}

.button-container {
  gap: 0.5rem; /* 버튼들 사이의 간격을 좁게 설정 */
}

button {
  padding: 12px 20px;
  font-size: 16px; /* 버튼 글자 크기 조정 */
  min-width: 180px; /* 버튼 최소 너비 설정 */
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

button:hover {
  transform: translateY(-3px);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

.alert {
  display: inline-block; /* 글자 크기에 맞게 박스 크기 조정 */
}

.response-box {
  max-width: 600px;
  margin: 0 auto;
  height: 100%; /* 답변 박스의 높이를 채우도록 설정 */
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>

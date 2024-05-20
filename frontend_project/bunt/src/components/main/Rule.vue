<template>
  <div class="d-flex align-items-center justify-content-center min-vh-100">
    <div id="app" class="container text-center mt-5">
      <h1 class="mb-4">AI와 함께 야구 규칙 알아보기</h1>
      <div class="button-container d-flex flex-wrap justify-content-center mb-4">
        <button @click="sendMessage('KBO의 롯데 자이언츠의 우승 확률은? 현재 10등이야.')" class="btn btn-primary m-1">롯데 자이언츠의 우승 확률</button>
        <button @click="sendMessage('KBO의 삼성 라이온즈의 우승 확률은? 현재 3등이야.')" class="btn btn-primary m-1">삼성 라이온즈의 우승 확률</button>
        <button @click="sendMessage('한국에서 야구장 먹거리가 가장 맛있는 곳은 어디야')" class="btn btn-primary m-1">야구장 맛집 1등은?</button>
        <button @click="sendMessage('대한민국 프로야구인 KBO에서 응원을 가장 잘하는 팀은?')" class="btn btn-primary m-1">야구장 응원 1등팀은?</button>
        <button @click="sendMessage('대한민국 프로야구인 KBO에서 가장 인기있는 야구 팀은?')" class="btn btn-primary m-1">가장 인기있는 야구팀은?</button>
        <button @click="sendMessage('야구에서 아웃이 되는 경우')" class="btn btn-success m-1">아웃이 되는 경우</button>
        <button @click="sendMessage('야구의 이닝 구성에 대해 설명해줘')" class="btn btn-success m-1">이닝 구성</button>
        <button @click="sendMessage('야구에서 투수의 역할')" class="btn btn-success m-1">투수의 역할</button>
        <button @click="sendMessage('야구에서 타자의 역할')" class="btn btn-success m-1">타자의 역할</button>
        <button @click="sendMessage('야구에서 수비 포지션')" class="btn btn-success m-1">수비 포지션</button>
        <button @click="sendMessage('야구에서 홈플레이트와 베이스')" class="btn btn-success m-1">홈플레이트와 베이스</button>
        <button @click="sendMessage('야구에서 삼진이란?')" class="btn btn-success m-1">삼진</button>
        <button @click="sendMessage('야구에서 볼넷이란?')" class="btn btn-success m-1">볼넷</button>
        <button @click="sendMessage('야구에서 보크이란?')" class="btn btn-success m-1">보크</button>
        <button @click="sendMessage('야구에서 피쳐클락이란?')" class="btn btn-success m-1">피쳐클락</button>
        <button @click="sendMessage('야구에서 도루란?')" class="btn btn-success m-1">도루</button>
        <button @click="sendMessage('야구에서 더블 플레이란?')" class="btn btn-success m-1">더블 플레이</button>
        <button @click="sendMessage('야구에서 희생 플라이란?')" class="btn btn-success m-1">희생 플라이</button>
        <button @click="sendMessage('야구에서 파울볼이란?')" class="btn btn-success m-1">파울볼</button>
        <button @click="sendMessage('야구에서 번트란?')" class="btn btn-success m-1">번트</button>
      </div>
      <div v-if="loading" class="alert alert-info w-100">응답을 기다리는 중...</div>
      <div v-else class="alert alert-success w-100 response-box">{{ response }}</div>
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
        console.error('API Key is not defined. Please check your .env file.');
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
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.button-container {
  gap: 0.5rem; /* 버튼들 사이의 간격을 좁게 설정 */
}

button {
  padding: 8px 16px;
  font-size: 14px; /* 버튼 글자 크기 조정 */
  min-width: 120px; /* 버튼 최소 너비 설정 */
}

.alert {
  display: inline-block; /* 글자 크기에 맞게 박스 크기 조정 */
}

.response-box {
  max-width: 600px;
  margin: 0 auto;
}
</style>

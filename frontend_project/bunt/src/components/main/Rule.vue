<template>
  <div id="app">
    <h1>Generative AI Chat</h1>
    <input v-model="userInput" placeholder="질문을 입력하세요" />
    <button @click="sendMessage">질문하기</button>
    <div v-if="loading">응답을 기다리는 중...</div>
    <div v-else>{{ response }}</div>
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
    async sendMessage() {
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
              parts: [{ text: 'Hello, I have 2 dogs in my house.' }],
            },
            {
              role: 'model',
              parts: [{ text: 'Great to meet you. What would you like to know?' }],
            },
          ],
          generationConfig: {
            maxOutputTokens: 100,
          },
        });

        const result = await chat.sendMessageStream(this.userInput);

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

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

input {
  padding: 8px;
  margin-right: 10px;
  width: 300px;
}

button {
  padding: 8px 16px;
}

div {
  margin-top: 20px;
}
</style>

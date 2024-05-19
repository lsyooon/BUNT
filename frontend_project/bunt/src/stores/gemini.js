import { GoogleGenerativeAI } from '@google/generative-ai';
import dotenv from 'dotenv';

dotenv.config();

// Access your API key as an environment variable
const apiKey = import.meta.env.VITE_GENERATIVE_AI_API_KEY;
if (!apiKey) {
  console.error('API Key is not defined. Please check your .env file.');
  process.exit(1);
}

const genAI = new GoogleGenerativeAI(apiKey);

async function run() {
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

    const msg = '야구 규칙에 대해서 설명해줘';

    const result = await chat.sendMessageStream(msg);
    for await (const item of result.stream) {
      console.log(item.candidates[0]?.content?.parts[0]?.text);
    }
  } catch (error) {
    console.error('Error:', error);
  }
}

run();

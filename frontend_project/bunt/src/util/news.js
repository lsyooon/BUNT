import { defineStore } from 'pinia';
import axios from 'axios';
import cheerio from 'cheerio';

export const useNewsStore = defineStore('news', {
  state: () => ({
    newsItems: [],
  }),
  actions: {
    async fetchNews() {
      try {
        const response = await axios.get('https://www.giantsclub.com/m/?pcode=535'); // 실제 URL을 여기에 넣으세요.
        const $ = cheerio.load(response.data);

        const newsItems = [];
        $('ul.mnews-list li').each((index, element) => {
          const title = $(element).find('strong').text().trim();
          const date = $(element).find('.mnews-date').text().trim();
          if (title && date) {
            newsItems.push({ title, date });
          }
        });

        this.newsItems = newsItems;
      } catch (error) {
        console.error('Error fetching news:', error);
      }
    },
  },
});

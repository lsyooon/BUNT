import { defineStore } from 'pinia';
import axios from 'axios';
import cheerio from 'cheerio';

const BASE_URL = 'https://your-website.com'; // 실제 사이트의 베이스 URL을 여기에 넣으세요.

export const useNewsStore = defineStore('news', {
  state: () => ({
    newsItems: [],
    newsContent: '',
  }),
  actions: {
    async fetchNews() {
      try {
        const response = await axios.get('https://www.giantsclub.com/m/?pcode=535'); // 실제 메인 페이지 URL을 여기에 넣으세요.
        const $ = cheerio.load(response.data);

        const newsItems = [];
        $('ul.mnews-list li').each((index, element) => {
          const title = $(element).find('strong').text().trim();
          const link = $(element).find('a').attr('href');
          const date = $(element).find('.mnews-date').text().trim();
          if (title && link && date) {
            newsItems.push({ title, link: `${BASE_URL}${link}`, date });
          }
        });

        this.newsItems = newsItems;
      } catch (error) {
        console.error('Error fetching news:', error);
      }
    },
    async fetchNewsContent(link) {
      try {
        const response = await axios.get(link);
        const $ = cheerio.load(response.data);

        const content = $('#frm > div > div.mboard-view-cont p').map((i, el) => $(el).text()).get().join('\n');
        this.newsContent = content || 'Content not found';
      } catch (error) {
        console.error('Error fetching news content:', error);
        this.newsContent = 'Error fetching content';
      }
    }
  },
});

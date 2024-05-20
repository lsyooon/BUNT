import { defineStore } from 'pinia';
import axios from 'axios';
import cheerio from 'cheerio';

const BASE_URL = 'https://www.giantsclub.com/m/';

export const useNewsStore = defineStore('news', {
  state: () => ({
    newsItems: [],
    newsContent: '',
  }),
  actions: {
    async fetchNews() {
      try {
        const response = await axios.get(`${BASE_URL}?pcode=535`);
        const $ = cheerio.load(response.data);

        const newsItems = [];
        $('ul.mnews-list li').each((index, element) => {
          const title = $(element).find('strong').text().trim();
          const link = $(element).find('a').attr('href');
          const date = $(element).find('.mnews-date').text().trim();
          if (title && link && date) {
            const fullLink = `${BASE_URL}${link}`;
            newsItems.push({ title, link: fullLink, date });
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

        // 모든 이미지 태그의 src를 절대 경로로 변환
        $('img').each((i, el) => {
          const src = $(el).attr('src');
          if (src && !src.startsWith('http')) {
            $(el).attr('src', `${BASE_URL}${src}`);
          }
        });

        // 필요한 경우 a 태그의 href도 절대 경로로 변환
        $('a').each((i, el) => {
          const href = $(el).attr('href');
          if (href && !href.startsWith('http')) {
            $(el).attr('href', `${BASE_URL}${href}`);
          }
        });

        // 첨부파일 리스트를 제외한 콘텐츠를 추출
        $('#frm > div > div.mboard-view-cont').find('.board-view-file').remove();

        // <a> 태그의 텍스트를 추출하고, 하이퍼링크는 유지
        $('#frm > div > div.mboard-view-cont a').each((i, el) => {
          const href = $(el).attr('href');
          const text = $(el).text().trim().split(' ')[0]; // 첫 번째 텍스트 부분만 가져옴
          $(el).replaceWith(`<a href="${href}" target="_blank">${text}</a>`);
        });

        const content = $('#frm > div > div.mboard-view-cont').html();
        this.newsContent = content || 'Content not found';
      } catch (error) {
        console.error('Error fetching news content:', error);
        this.newsContent = 'Error fetching content';
      }
    }
  },
});

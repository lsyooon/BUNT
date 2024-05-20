import { defineStore } from 'pinia';
import axios from 'axios';
import cheerio from 'cheerio';

const BASE_URL = 'https://www.samsunglions.com/intro/intro02.asp?page=';

export const useNewsStore = defineStore('news', {
  state: () => ({
    newsItems: [],
    newsContent: '',
    newsTitle: '', // 뉴스 제목을 저장할 상태 추가
    newsDate: '', // 뉴스 날짜를 저장할 상태 추가
    currentPage: 1,
    totalPages: 10, // 예시로 총 10페이지 설정, 실제 페이지 수에 맞게 조정
  }),
  actions: {
    async fetchNews(page = 1) {
      try {
        const response = await axios.get(`${BASE_URL}${page}&keyword=&search=&act=&idx=0`);
        const $ = cheerio.load(response.data);

        const newsItems = [];
        $('tbody tr').each((index, element) => {
          const title = $(element).find('.tit a').text().trim();
          const link = $(element).find('.tit a').attr('href');
          const date = $(element).find('.dat').text().trim();
          if (title && link && date) {
            const fullLink = `https://www.samsunglions.com${link}`;
            newsItems.push({ title, link: fullLink, date });
          }
        });

        this.newsItems = newsItems;
        this.currentPage = page;
      } catch (error) {
        console.error('Error fetching news:', error);
      }
    },
    async fetchNewsContent(link) {
      try {
        const response = await axios.get(link);
        const $ = cheerio.load(response.data);

        // 뉴스 제목 가져오기
        const title = $('.mArticle2 .tit h4').text().trim();
        this.newsTitle = title || '제목을 가져올 수 없습니다.';

        // 뉴스 날짜 가져오기
        const date = $('.mArticle2 .tit2 .dat em').text().trim();
        this.newsDate = date || '날짜를 가져올 수 없습니다.';

        // 모든 이미지 태그의 src를 절대 경로로 변환하고 가운데 정렬을 위한 스타일 추가
        $('img').each((i, el) => {
          const src = $(el).attr('src');
          if (src && !src.startsWith('http')) {
            $(el).attr('src', `${BASE_URL}${src}`);
          }
          $(el).attr('style', 'display: block; margin: 0 auto;'); // 이미지 가운데 정렬 스타일 추가
        });

        // 필요한 경우 a 태그의 href도 절대 경로로 변환
        $('a').each((i, el) => {
          const href = $(el).attr('href');
          if (href && !href.startsWith('http')) {
            $(el).attr('href', `${BASE_URL}${href}`);
          }
        });

        // 첨부파일 리스트를 제외한 콘텐츠를 추출
        $('.mArticle2 .con').find('.board-view-file').remove();

        // <a> 태그의 텍스트를 추출하고, 하이퍼링크는 유지
        $('.mArticle2 .con a').each((i, el) => {
          const href = $(el).attr('href');
          const text = $(el).text().trim().split(' ')[0]; // 첫 번째 텍스트 부분만 가져옴
          $(el).replaceWith(`<a href="${href}" target="_blank">${text}</a>`);
        });

        const content = $('.mArticle2 .con').html();
        this.newsContent = content || 'Content not found';
      } catch (error) {
        console.error('Error fetching news content:', error);
        this.newsContent = 'Error fetching content';
      }
    },
    async goToPreviousPage() {
      if (this.currentPage > 1) {
        await this.fetchNews(this.currentPage - 1);
      }
    },
    async goToNextPage() {
      if (this.currentPage < this.totalPages) {
        await this.fetchNews(this.currentPage + 1);
      }
    }
  },
});

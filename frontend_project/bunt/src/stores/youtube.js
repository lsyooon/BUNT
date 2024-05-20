// stores/youtube.js
import { defineStore } from 'pinia';
import axios from 'axios';

export const useYoutubeStore = defineStore('youtube', {
    state: () => ({
        videos: [],
        selectedVideo: null
    }),
    actions: {
        async youtubeSearch(keyword) {
            try {
                const response = await axios.get('https://www.googleapis.com/youtube/v3/search', {
                    params: {
                        // key: import.meta.env.VITE_YOUTUBE_API_KEY,
                        // key: import.meta.env.VITE_YOUTUBE_API_KEY2,
                        part: 'snippet',
                        maxResults: 10,
                        q: keyword,
                        type: 'video'
                    }
                });
                this.videos = response.data.items;
                if (this.videos.length > 0) {
                    this.selectedVideo = this.videos[0];  // 첫 번째 비디오를 선택
                }
            } catch (error) {
                console.error('YouTube API 요청 오류:', error);
            }
        },
        selectVideo(video) {
            this.selectedVideo = video;
        },
        clickVideo(video) {
            this.selectedVideo = video;
            console.log('동영상 클릭:', video);
            // 추가적인 동영상 클릭 핸들링 로직
        }
    }
});

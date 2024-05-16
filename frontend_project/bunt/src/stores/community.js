import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'

const REST_BOARD_API = 'http://localhost:8080/api/board'

export const useCommunityStore = defineStore('community', () => {

    const boardList = ref([])
    const getBoardList = function () {
        axios.get(REST_BOARD_API)
            .then((response) => {
                boardList.value = response.data
            })
    }

    return { boardList, getBoardList}

})
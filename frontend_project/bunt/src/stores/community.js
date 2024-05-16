import { ref } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

const REST_BOARD_API = 'http://localhost:8080/api/board/'

export const useCommunityStore = defineStore('community', () => {

    const boardList = ref([])
    const getBoardList = function () {
        axios.get(REST_BOARD_API)
            .then((response) => {
                boardList.value = response.data;
            })
    }

    const searchBoardList = function (searchCondition) {
        axios.get(REST_BOARD_API, {
            params: searchCondition
        })
            .then((res) => {
                boardList.value = res.data
            })
    }

    return { boardList, getBoardList, searchBoardList}

})
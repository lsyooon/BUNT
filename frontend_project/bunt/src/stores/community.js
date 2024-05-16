import { ref } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from "@/router";

const REST_BOARD_API = 'http://localhost:8080/api/board'

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

    const createBoard = function (board) {
        // user_id가 아니라 writer로 바꿔야할듯
        axios({
            url: REST_BOARD_API,
            method: 'POST',
            data: board
        })
            .then(() => {
                router.push({name: 'boardList'})
            })
            .catch((err) => {
                console.log(err)
            })
    }

    const board = ref({})
    const getBoard = function (id) {
        axios.get(`${REST_BOARD_API}/${id}`)
            .then((response) => {
                board.value = response.data
            })
    }

    return { boardList, getBoardList, searchBoardList, createBoard, board, getBoard}

})
import { ref } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from "@/router";
import {useRoute} from "vue-router";
const route = useRoute();

const REST_BOARD_API = 'http://localhost:8080/api/board'

export const useCommunityStore = defineStore('community', () => {

    const boardList = ref([])
    const getBoardList = function (teamId) {
        axios.get(`${REST_BOARD_API}/${teamId}`)
            .then((response) => {
                boardList.value = response.data;
            })
    }

    const searchBoardList = function (searchCondition, teamId) {
        axios.get(`${REST_BOARD_API}/${teamId}`, {
            params: searchCondition, teamId
        })
            .then((res) => {
                boardList.value = res.data
            })
    }

    const createBoard = function (board) {
        axios.post(REST_BOARD_API, board)
            .then(() => {
                const teamId = router.currentRoute.value.params.teamId;
                router.push(`/${teamId}/community`);
            })
            .catch((err) => {
                console.log(err);
            });
    }

    const board = ref({})
    const getBoard = function (teamId, id) {
        axios.get(`${REST_BOARD_API}/${teamId}/${id}`)
            .then((response) => {
                board.value = response.data
            })
    }

    const updateBoard = function (id) {
        const teamId = router.currentRoute.value.params.teamId;
        axios.put(`${REST_BOARD_API}/${teamId}/${id}`, board.value)
            .then(() => {
                router.push(`/${teamId}/community/${id}`);
            })
    }



    return { boardList, getBoardList, searchBoardList, createBoard, board, getBoard, updateBoard}

})
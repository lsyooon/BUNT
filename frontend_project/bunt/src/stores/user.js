import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'

const REST_USER_API = 'http://localhost:8080/api'

export const useUserStore = defineStore('user', () => {

    const login = (user) => {
        axios.post(`${REST_USER_API}/login`, user )
            .then(() => {
                console.log(user)
                router.push({path : '/'})
                sessionStorage.setItem('loginUser', JSON.stringify(user))
            })
            .catch((err) => {
                console.log(err);
            })
    }

    const logout = () => {
        axios.get(`${REST_USER_API}/logout`)
            .then((response) => {
                sessionStorage.removeItem('loginUser')
                router.push({path: "/"})
            })
            .catch((err)=> {
                console.log(err);
            })
    }

    const join = (user) => {
        axios.post(`${REST_USER_API}/join`, user )
            .then(() => {
                router.push({path : '/'})
            })
            .catch((err) => {
                console.log(err)
            })

    }


    return { login, logout, join }
})

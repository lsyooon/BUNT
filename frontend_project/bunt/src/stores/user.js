import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'

const REST_USER_API = 'http://localhost:8080/api'

export const useUserStore = defineStore('user', () => {

  const login = (user) => {
    axios.post(`${REST_USER_API}/login`, user)
      .then(() => {
        console.log(user)
        router.back();
        sessionStorage.setItem('loginUser', JSON.stringify(user))
      })
      .catch((err) => {
        console.log(err)
      })
  }

  const logout = () => {
    axios.get(`${REST_USER_API}/logout`)
      .then((response) => {
        sessionStorage.removeItem('loginUser')
        router.push({ path: '/' })
      })
      .catch((err) => {
        console.log(err)
      })
  }

  const join = (user) => {
    axios.post(`${REST_USER_API}/join`, user)
      .then(() => {
        router.back();
      })
      .catch((err) => {
        console.log(err)
      })

  }

  const user = ref(null)

  const getUserById = async (id) => {
    try {
      const response = await axios.get(`${REST_USER_API}/read/${id}`)
      if (response.data) {
        user.value = response.data
      } else {
        console.log('No user data found')
      }
    } catch (error) {
      console.error(error)
    }
  }

  const modifyUser = async (userInfo) => {
    try {
      const response = await axios.put(`${REST_USER_API}/modify`, userInfo);
      return response.data;
    } catch (error) {
      console.error('modifyUser 오류:', error);
      throw error;
    }
  }

  const deleteUserById = async (id) => {
    try {
      const response = await axios.delete(`${REST_USER_API}/quit/${id}`);
      return response.data;
    } catch (error) {
      console.error('deleteUserById 오류:', error);
      throw error;
    }
  }

  return { login, logout, join, getUserById, modifyUser, deleteUserById, user }
})

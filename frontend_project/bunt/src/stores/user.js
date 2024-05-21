import { ref } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import router from '@/router';

const REST_USER_API = 'http://localhost:8080/api';

export const useUserStore = defineStore('user', () => {
  const user = ref(null);
  const isDuplicateId = ref(false);

  const login = (user) => {
    axios.post(`${REST_USER_API}/login`, user)
      .then(() => {
        console.log(user);
        sessionStorage.setItem('loginUser', JSON.stringify(user));
        router.push('/'); // 이전 경로로 이동
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const logout = () => {
    axios.get(`${REST_USER_API}/logout`)
      .then(() => {
        sessionStorage.removeItem('loginUser');
        router.push('/'); // 로그아웃 후 메인 페이지로 이동
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const join = (user) => {
    axios.post(`${REST_USER_API}/join`, user)
      .then(() => {
        router.push('/'); // 회원가입 후 이전 경로로 이동
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const getUserById = async (id) => {
    try {
      const response = await axios.get(`${REST_USER_API}/read/${id}`);
      if (response.data) {
        user.value = response.data;
        isDuplicateId.value = true;
      } else {
        user.value = null;
        isDuplicateId.value = false;
      }
    } catch (error) {
      console.error(error);
    }
  };

  const modifyUser = async (userInfo) => {
    try {
      const response = await axios.put(`${REST_USER_API}/modify`, userInfo);
      return response.data;
    } catch (error) {
      console.error('modifyUser 오류:', error);
      throw error;
    }
  };

  const deleteUserById = async (id) => {
    try {
      const response = await axios.delete(`${REST_USER_API}/quit/${id}`);
      return response.data;
    } catch (error) {
      console.error('deleteUserById 오류:', error);
      throw error;
    }
  };


  return { login, logout, join, getUserById, modifyUser, deleteUserById, user, isDuplicateId };
});

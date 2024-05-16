<script setup>
import { ref } from 'vue';
import {useUserStore} from "@/stores/user.js";

const store = useUserStore()

const user = ref({
  id: '',
  password: '',
  email: '',
  name: '',
  nickname: '',
  age: '',
  phoneNumber: '',
  address: ''
})

const join = () => {
  store.join(user.value)
}

</script>

<template>
  <div>
    <fieldset>
      <label for="id">아이디</label>
      <input type="text" name="name" v-model="user.id">
      <br>
      <label for="password">비밀번호</label>
      <input type="password" name="password" v-model="user.password">
      <br>
      <label for="email">이메일</label>
      <input type="text" name="email" v-model="user.email">
      <br>
      <label for="name">이름</label>
      <input type="text" name="name" v-model="user.name">
      <br>
      <label for="nickname">닉네임</label>
      <input type="text" name="nickname" v-model="user.nickname">
      <br>
      <label for="age">나이</label>
      <input type="number" name="age" v-model="user.age">
      <br>
      <label for="phoneNumber">전화번호</label>
      <input type="tel" name="phoneNumber" v-model="user.phoneNumber">
      <br>
      <label for="address">주소</label>
      <input type="text" name="address" v-model="user.address">
      <br>
      <button @click="join">join</button>
    </fieldset>
  </div>
</template>

<style scoped>

</style>

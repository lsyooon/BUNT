<script setup>
import { ref } from 'vue';
import {useUserStore} from "@/stores/user.js";

const store = useUserStore()

const user = ref({
  id: '',
  password: ''
})

const login = function() {
  store.login(user.value)
}
</script>

<template>
  <div>
    <h2>Login</h2>
    <fieldset>
      <label for="id">아이디</label>
      <input type="text" name="id" v-model="user.id">
      <br>
      <label for="password">비밀번호</label>
      <input type="password" name="password" v-model="user.password">
      <br>
      <button @click="login">submit</button>
    </fieldset>
  </div>
</template>

<style scoped>

</style>

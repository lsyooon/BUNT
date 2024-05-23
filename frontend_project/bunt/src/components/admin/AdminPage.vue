<template>
  <div class="container mt-5">
    <div v-if="userList">
      <table class="table table-hover">
        <thead>
        <tr>
          <th>회원번호</th>
          <th>아이디</th>
          <th>이름</th>
          <th>이메일</th>
          <th>닉네임</th>
          <th>나이</th>
          <th>전화번호</th>
          <th>주소</th>
          <th>가입날짜</th>
          <th>수정날짜</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="user in userList" :key="user.id">
          <td>{{ user.userId }}</td>
          <td>{{ user.id }}</td>
          <td>{{ user.name }}</td>
          <td>{{ user.email }}</td>
          <td>{{ user.nickname }}</td>
          <td>{{ user.age }}</td>
          <td>{{ user.phoneNumber }}</td>
          <td>{{ user.address }}</td>
          <td>{{ user.joinDate }}</td>
          <td>{{ user.modDate }}</td>
          <td>
            <button class="btn btn-danger btn-sm" @click="deleteUser(user.id)">Delete</button>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <div v-else>
      <p>No users found.</p>
    </div>
  </div>
</template>

<script setup>
import { onMounted, watchEffect } from 'vue'
import { useUserStore } from '@/stores/user';

let userStore = useUserStore();

onMounted(() => {
  console.log("Admin page mounted, fetching user list");
  init();
  userStore = useUserStore();
});

const init = async () => {

  userList = await userStore.getUserList();
  console.log(userList)
}

const deleteUser = (id) => {
  if (confirm('Are you sure you want to delete this user?')) {
    userStore.deleteUserById(id).then(() => {
      userStore.getUserList(); // Refresh user list after deletion
    }).catch(err => {
      console.error('Error deleting user:', err);
    });
  }
};

let { userList } = userStore;
watchEffect(() => {
  console.log("User list updated:", userList.value);
});
</script>


<style scoped>
.container {
  max-width: 80%;
  margin-top: 5%;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}

.table-hover tbody tr:hover {
  background-color: #f5f5f5;
}

.btn-danger {
  font-size: 0.8rem;
}
</style>

<script setup>

</script>

<template>
  <div>
    <header>
      <nav>
        <div class="d-flex justify-content-between">
          <img src="../../assets/image_logo/KBO_logo.png" style="width: 100px " alt="">
          <RouterLink to="">NEWS</RouterLink>
          <RouterLink to="">COMMUNITY</RouterLink>
          <RouterLink to="">SHOP</RouterLink>
          <img src="@/assets/image_icons/login_icon.png" style="width:40px; margin-left: 50px" alt="">
        </div>
      </nav>
    </header>
  </div>
</template>

<style scoped>
.router-link-exact-active {
  text-decoration: none;
  font-weight: bold;
  font-size: 20px;
  margin-top: 5px;
  color: white;
}

nav {
  box-sizing: border-box;
  background-color: #24418E;
  padding-top: 15px;
  padding-bottom: 15px;
  padding-left: 15px;
  padding-right: 25px;
}
</style>
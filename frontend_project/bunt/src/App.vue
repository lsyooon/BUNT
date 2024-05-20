<script setup>
import {RouterView} from 'vue-router'
import Footer from "@/components/common/Footer.vue";
import TheHeaderNav from "@/components/common/TheHeaderNav.vue";
</script>

<template>
  <TheHeaderNav/>
  <main>

    <RouterView/>
  </main>
  <Footer/>
</template>

<style>

main {
  min-height: 100%;
  height: auto;
  position: relative;
  padding-bottom: 200px;
}

footer {
  height: 30%;
  position: relative;
  transform: translateY(-100%);
}
</style>

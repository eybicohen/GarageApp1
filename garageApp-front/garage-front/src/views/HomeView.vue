<template>
  <v-app style="background-color: #fdf1d3">
    <MainPage style="margin-top: 30vh" />
    <router-view></router-view>
  </v-app>
</template>

<script>
import MainPage from "../components/MainPage.vue";

export default {
  name: "HomeView",
  data: () => ({
    //
  }),
  components: { MainPage },
};
</script>

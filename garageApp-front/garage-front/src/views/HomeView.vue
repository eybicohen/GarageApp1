<template>
  <v-app style="background-color: rgba(170, 162, 80, 0.2)">
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

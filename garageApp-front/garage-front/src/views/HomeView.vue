<template>
  <v-app>
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

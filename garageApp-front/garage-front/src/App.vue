<template>
  <v-app>
    <v-app-bar
      absolute
      dark
      shrink-on-scroll
      prominent
      src="./assets/pexels-cottonbro-studio-4488637.jpg"
      fade-img-on-scroll
    >
      <template v-slot:img="{ props }">
        <v-img
          v-bind="props"
          gradient="to top right, rgba(100,115,201,.7), rgba(25,32,72,.7)"
        ></v-img>
      </template>

      <v-col class="offset-4">
        <v-app-bar-title
          style="font-size: 63px; font-family: Cursive"
          class="ml-16"
          >GarageApp</v-app-bar-title
        >
      </v-col>
      <span>{{ user.firstName }}</span>
      <v-spacer></v-spacer>

      <v-btn
        color="blue-grey"
        class="ma-2 white--text"
        @click="logout"
        v-if="isUserConnected"
      >
        logout
        <v-icon right dark> mdi-logout-variant</v-icon>
      </v-btn>
      <v-menu bottom left v-if="isUserConnected">
        <template v-slot:activator="{ on, attrs }">
          <v-btn dark icon v-bind="attrs" v-on="on">
            <v-app-bar-nav-icon></v-app-bar-nav-icon>
          </v-btn>
        </template>

        <v-list>
          <v-list-item>
            <router-link to="/" style="text-decoration: none; color: inherit">
              <v-btn color="blue-grey" class="ma-2 white--text"
                >home
                <v-icon right dark> mdi-home-outline</v-icon>
              </v-btn>
            </router-link>
          </v-list-item>
          <v-list-item>
            <router-link to="/" style="text-decoration: none; color: inherit">
              <v-btn color="blue-grey" class="ma-2 white--text"
                >donate
                <v-icon right dark> mdi-hand-coin-outline</v-icon>
              </v-btn>
            </router-link>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>
    <router-view></router-view>
  </v-app>
</template>

<script>
import MainPage from "./components/MainPage.vue";

export default {
  name: "App",
  data: () => ({
    isUserConnected: false,
  }),
  computed: {
    user() {
      if (!(Object.keys(this.$store.state.user).length === 0)) {
        this.isUserConnected = true;
      }
      return this.$store.state.user;
    },
  },
  mounted() {
    if (JSON.parse(localStorage.getItem("user")) === null) {
      this.$router.push({ name: "login" });
    } else if (Object.keys(this.$store.state.user).length === 0) {
      this.$store.commit(
        "changeUser",
        JSON.parse(localStorage.getItem("user"))
      );
    }
  },
  components: { MainPage },
  methods: {
    logout() {
      localStorage.clear();
      this.$store.commit("changeUser", {});
      this.isUserConnected = false;
      this.$router.push({ name: "login" });
    },
  },
};
</script>

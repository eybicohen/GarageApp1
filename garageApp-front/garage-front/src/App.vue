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
      <v-btn
        icon
        color="lime lighten-1"
        class="white--text"
        @click="logout"
        v-if="this.$store.state.isUserConnected"
      >
        <v-icon dark>mdi-logout-variant</v-icon>
      </v-btn>
      <v-col id="col">
        <div @click="redirect" style="cursor: pointer">
          <v-app-bar-title id="title">GarageApp</v-app-bar-title>
        </div>
      </v-col>
      <v-spacer></v-spacer>

      <router-link
        to="/"
        style="text-decoration: none; color: inherit"
        v-if="this.$store.state.isUserConnected"
      >
        <v-btn icon color="lime lighten-1" class="white--text">
          <v-icon dark> mdi-home-outline</v-icon>
        </v-btn>
      </router-link>
      <!-- <v-menu bottom left v-if="this.$store.state.isUserConnected" id="menu">
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
        </v-list> -->
      <!-- </v-menu> -->
    </v-app-bar>
    <router-view></router-view>
  </v-app>
</template>

<script>
import MainPage from "./components/MainPage.vue";
import users from "@/api/users";
import Swal from "sweetalert2";

export default {
  name: "App",
  data: () => ({}),
  mounted() {
    if (JSON.parse(localStorage.getItem("access_token")) === null) {
      this.$router.push({ name: "login" });
    } else if (Object.keys(this.$store.state.user).length === 0) {
      this.$store.commit(
        "changeUser",
        JSON.parse(localStorage.getItem("access_token"))
      );
      this.$store.commit("changeUserConnected", true);
    } else {
      this.$store.commit("changeUserConnected", true);
    }
  },

  components: { MainPage },
  methods: {
    async logout() {
      const isOnly = await users.isOnlyGoogleUser();
      if (isOnly) {
        const res = await Swal.fire({
          title: "Hello google user!",
          text: "would you like to be registered to our site and be able to connect not only with google?",
          showCancelButton: true,
          confirmButtonText: "Yes",
          cancelButtonText: "No i'm good",
          showClass: {
            popup: "animate__animated animate__fadeInDown",
          },
          hideClass: {
            popup: "animate__animated animate__fadeOutUp",
          },
        });

        if (res.isConfirmed) {
          const res1 = await Swal.fire({
            title: "enter your password",
            input: "text",
            confirmButtonText: "Yes",
            showLoaderOnConfirm: true,
            preConfirm: async (text) => {
              const pattern = /^[a-zA-Z0-9]*$/;
              const res2 = text.length >= 8 && pattern.test(text);
              if (!res2) {
                Swal.showValidationMessage(
                  "Password must contain at least 8 charecters and only letters or numbers"
                );
              } else {
                await users.changePassword(text);
              }
            },
            showClass: {
              popup: "animate__animated animate__fadeInDown",
            },
            hideClass: {
              popup: "animate__animated animate__fadeOutUp",
            },
          });
        }
      }

      localStorage.clear();
      this.$store.commit("changeUserConnected", false);
      this.$store.commit("changeUser", {});
      this.$router.push({ name: "login" });
    },
    redirect() {
      if (this.$store.state.isUserConnected) {
        this.$router.push({ name: "home" });
      }
    },
  },
};
</script>

<style>
@media screen and (min-width: 1000px) {
  #title {
    font-size: 63px;
    font-family: Cursive;
  }
  #col {
    margin-left: 32vw;
  }
}
@media screen and (max-width: 1000px) {
  #title {
    margin-top: 6vh;
    font-size: 2rem;
  }
}
</style>

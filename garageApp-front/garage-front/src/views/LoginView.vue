<template>
  <v-app id="inspire">
    <v-content>
      <v-container fluid fill-height class="mt-10">
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-card class="elevation-12">
              <v-toolbar dark color="indigo lighten-1">
                <v-toolbar-title style="margin-left: 250px; font-family: italic"
                  >התחבר לחשבונך</v-toolbar-title
                >
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field
                    v-model="email"
                    name="login"
                    label="אימייל"
                    type="text"
                  ></v-text-field>
                  <v-text-field
                    v-model="password"
                    id="password"
                    name="password"
                    label="סיסמה"
                    type="password"
                  ></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="success" class="ma-2 white--text" @click="submit">
                  התחבר
                  <v-icon right dark> mdi-login-variant </v-icon>
                </v-btn>
                <v-btn
                  color="blue-grey"
                  class="ma-2 white--text"
                  to="/register"
                >
                  הרשמה
                  <v-icon right dark> mdi-account-plus </v-icon>
                </v-btn>
                <p class="mt-4">?עוד לא רשום</p>
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
import users from "@/api/users.js";
import { mapState, mapMutations } from "vuex";
export default {
  name: "Login",
  props: {
    source: String,
  },
  data: () => ({
    email: "",
    password: "",
  }),
  methods: {
    async submit() {
      const user = await users.getUserByEmail(this.email);
      if (this.password == user.password) {
        this.$store.commit("changeUser", user);
        localStorage.setItem('user', JSON.stringify(user));
        this.$router.push({ name: "home" });
      } else {
        alert("one of the details is wrong");
      }
    },
  },
};
</script>

<style></style>

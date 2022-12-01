<template>
  <v-app id="inspire" style="margin-top: 24vh">
    <v-content>
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="8">
            <v-card class="elevation-12">
              <v-window v-model="step">
                <v-window-item :value="1">
                  <v-row>
                    <v-col cols="12" md="8">
                      <v-card-text class="mt-12">
                        <h1
                          class="text-center display-2 #FFE082--text text--#FFE082"
                          style="color: #d4e157"
                        >
                          Sign in to Care tracking
                        </h1>
                        <div class="text-center mt-4">
                          <v-btn class="mx-2" fab color="black" outlined>
                            <v-icon>fab fa-facebook-f</v-icon>
                          </v-btn>

                          <v-btn class="mx-2" fab color="black" outlined>
                            <v-icon>fab fa-google-plus-g</v-icon>
                          </v-btn>
                          <v-btn class="mx-2" fab color="black" outlined>
                            <v-icon>fab fa-linkedin-in</v-icon>
                          </v-btn>
                        </div>
                        <h4 class="text-center mt-4">
                          Ensure your email for registration
                        </h4>
                        <v-form>
                          <v-text-field
                            label="Email"
                            name="Email"
                            prepend-icon="mdi-email"
                            type="text"
                            color="#D4E157"
                            v-model="email1"
                            required
                          />

                          <v-text-field
                            id="password"
                            label="Password"
                            name="password"
                            prepend-icon="mdi-lock"
                            type="password"
                            color="#D4E157"
                            v-model="password1"
                            required
                          />
                        </v-form>
                      </v-card-text>
                      <div class="text-center mb-3">
                        <v-btn
                          rounded
                          color="success"
                          dark
                          @click="submit()"
                          class="ml-1"
                          >SIGN IN</v-btn
                        >
                      </div>
                    </v-col>
                    <v-col cols="12" md="4" class="lime lighten-1">
                      <v-card-text class="white--text mt-12">
                        <h1 class="text-center display-1">Hello, Friend!</h1>
                        <h5 class="text-center">
                          Enter your personal details and start journay with us
                        </h5>
                      </v-card-text>
                      <div class="text-center">
                        <v-btn rounded outlined dark @click="move()"
                          >SIGN UP</v-btn
                        >
                      </div>
                    </v-col>
                  </v-row>
                </v-window-item>
                <v-window-item :value="2">
                  <v-row class="fill-height">
                    <v-col cols="12" md="4" class="lime lighten-1">
                      <v-card-text class="white--text mt-12">
                        <h1 class="text-center display-1">Welcome Back!</h1>
                        <h5 class="text-center">
                          To Keep connected with us please login with your
                          personnel info
                        </h5>
                      </v-card-text>
                      <div class="text-center">
                        <v-btn rounded outlined dark @click="move()"
                          >Sign in</v-btn
                        >
                      </div>
                    </v-col>

                    <v-col cols="12" md="8">
                      <v-card-text class="mt-12">
                        <h1
                          class="text-center display-2"
                          style="color: #d4e157"
                        >
                          Create Account
                        </h1>
                        <div class="text-center mt-4">
                          <v-btn class="mx-2" fab color="black" outlined>
                            <v-icon>fab fa-facebook-f</v-icon>
                          </v-btn>

                          <v-btn class="mx-2" fab color="black" outlined>
                            <v-icon>fab fa-google-plus-g</v-icon>
                          </v-btn>
                          <v-btn class="mx-2" fab color="black" outlined>
                            <v-icon>fab fa-linkedin-in</v-icon>
                          </v-btn>
                        </div>
                        <h4 class="text-center mt-4">
                          Ensure your email for registration
                        </h4>
                        <v-form>
                          <v-text-field
                            label="FirstName"
                            name="FirstName"
                            prepend-icon="mdi-account"
                            type="text"
                            color="#D4E157"
                            v-model="name"
                            :error-messages="nameErrors"
                            :counter="10"
                            required
                            @input="$v.name.$touch()"
                            @blur="$v.name.$touch()"
                          />
                          <v-text-field
                            label="LastName"
                            name="LastName"
                            prepend-icon="mdi-account-group"
                            type="text"
                            color="#D4E157"
                            v-model="lastName"
                            :error-messages="LastNameErrors"
                            :counter="10"
                            required
                            @input="$v.lastName.$touch()"
                            @blur="$v.lastName.$touch()"
                          />
                          <v-text-field
                            label="Email"
                            name="Email"
                            prepend-icon="mdi-email"
                            type="text"
                            color="#D4E157"
                            v-model="email"
                            :error-messages="emailErrors"
                            required
                            @input="$v.email.$touch()"
                            @blur="$v.email.$touch()"
                          />

                          <v-text-field
                            id="password"
                            label="Password"
                            name="password"
                            prepend-icon="mdi-lock"
                            type="password"
                            color="#D4E157"
                            v-model="password"
                            :error-messages="passwordErrors"
                            required
                            @input="$v.password.$touch()"
                            @blur="$v.password.$touch()"
                          />
                        </v-form>
                      </v-card-text>
                      <div class="text-center mt-n5 mb-3">
                        <v-btn @click="clear" rounded class="mr-1">
                          clear
                        </v-btn>
                        <v-btn
                          rounded
                          color="success"
                          dark
                          @click="submit"
                          class="ml-1"
                          >SIGN UP</v-btn
                        >
                      </div>
                    </v-col>
                  </v-row>
                </v-window-item>
              </v-window>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
import { validationMixin } from "vuelidate";
import {
  required,
  maxLength,
  email,
  minLength,
  alphaNum,
} from "vuelidate/lib/validators";
import users from "@/api/users";

export default {
  name: "Login",

  mixins: [validationMixin],

  validations: {
    name: { required, maxLength: maxLength(10) },
    lastName: { required, maxLength: maxLength(10) },
    email: { required, email },
    password: { required, minLength: minLength(8), alphaNum },
    email1: { required, email },
    password1: { required, minLength: minLength(8), alphaNum },
  },

  data: () => ({
    step: 1,
    name: "",
    lastName: "",
    email: "",
    password: "",
    email1: "",
    password1: "",
  }),
  props: {
    source: String,
  },
  mounted() {
    if (JSON.parse(localStorage.getItem("user")) !== null) {
      this.$router.push({ name: "home" });
    }
  },
  computed: {
    nameErrors() {
      const errors = [];
      if (!this.$v.name.$dirty) return errors;
      !this.$v.name.maxLength &&
        errors.push("Name must be at most 10 characters long");
      !this.$v.name.required && errors.push("FirstName is required.");
      return errors;
    },
    LastNameErrors() {
      const errors = [];
      if (!this.$v.lastName.$dirty) return errors;
      !this.$v.lastName.maxLength &&
        errors.push("LastName must be at most 10 characters long");
      !this.$v.lastName.required && errors.push("LastName is required.");
      return errors;
    },
    emailErrors() {
      const errors = [];
      if (!this.$v.email.$dirty) return errors;
      !this.$v.email.email && errors.push("Must be valid e-mail");
      !this.$v.email.required && errors.push("E-mail is required");
      return errors;
    },
    passwordErrors() {
      const errors = [];
      if (!this.$v.password.$dirty) return errors;
      !this.$v.password.minLength &&
        errors.push("Password must have at least 8 charachters");
      !this.$v.password.alphaNum &&
        errors.push("Password must contain only letters or numbers");
      !this.$v.password.required && errors.push("Password is required");
      return errors;
    },
  },

  methods: {
    async submit() {
      if (!this.$v.$error) {
        if (this.step === 1) {
          const user = await users.getUserByEmail(this.email1);
          if (this.password1 == user.password) {
            this.$store.commit("changeUser", user);
            localStorage.setItem("user", JSON.stringify(user));
            this.$router.push({ name: "home" });
          } else {
            alert("one of the details is wrong");
          }
        } else {
          this.$v.$touch();
          try {
            const user = {
              firstName: this.name,
              lastName: this.lastName,
              email: this.email,
              password: this.password,
            };
            await users.registerUser(user);
            this.step--;
          } catch {
            alert("oops looks like you already have an acount");
          }
        }
      }
    },
    clear() {
      this.$v.$reset();
      this.name = "";
      this.lastName = "";
      this.email = "";
      this.password = "";
    },
    clear1() {
      this.$v.$reset();
      this.email1 = "";
      this.password1 = "";
    },
    move() {
      if (this.step == 1) {
        this.clear1();
        this.step++;
      } else {
        this.clear();
        this.step--;
      }
    },
  },
};
</script>

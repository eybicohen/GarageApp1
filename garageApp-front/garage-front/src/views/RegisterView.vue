<template>
  <v-container
    ><form style="margin-top: 30vh; width: 40vw; margin-left: 28vw">
      <v-text-field
        v-model="name"
        :error-messages="nameErrors"
        :counter="10"
        label="FirstName"
        required
        @input="$v.name.$touch()"
        @blur="$v.name.$touch()"
      ></v-text-field>
      <v-text-field
        v-model="lastName"
        :error-messages="LastNameErrors"
        :counter="10"
        label="LastName"
        required
        @input="$v.lastName.$touch()"
        @blur="$v.lastName.$touch()"
      ></v-text-field>
      <v-text-field
        v-model="email"
        :error-messages="emailErrors"
        label="E-mail"
        required
        @input="$v.email.$touch()"
        @blur="$v.email.$touch()"
      ></v-text-field>
      <v-text-field
        v-model="password"
        :error-messages="passwordErrors"
        label="Password"
        required
        @input="$v.password.$touch()"
        @blur="$v.password.$touch()"
      ></v-text-field>

      <v-btn class="mr-4" @click="submit" color="success"> submit </v-btn>
      <v-btn @click="clear"> clear </v-btn>
      <v-btn to="/login" class="ml-5" color="primary"> login </v-btn>
    </form></v-container
  >
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
  mixins: [validationMixin],

  validations: {
    name: { required, maxLength: maxLength(10) },
    lastName: { required, maxLength: maxLength(10) },
    email: { required, email },
    password: { required, minLength: minLength(8), alphaNum },
  },

  data: () => ({
    name: "",
    lastName: "",
    email: "",
    password: "",
  }),

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
      this.$v.$touch();
      if (!this.$v.$error) {
        try {
          const user = {
            firstName: this.name,
            lastName: this.lastName,
            email: this.email,
            password: this.password,
          };
          await users.registerUser(user);
          this.$router.push({ name: "login" });
        } catch {
          alert("oops looks like you already have an acount");
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
  },
};
</script>

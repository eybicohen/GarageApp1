<template>
  <form style="margin-top: 30vh; width: 40vw; margin-left: 28vw">
    <v-text-field
      v-model="carName"
      :error-messages="carNameErrors"
      :counter="10"
      label="car name"
      required
      @input="$v.carName.$touch()"
      @blur="$v.carName.$touch()"
    ></v-text-field>
    <v-text-field
      v-model="company"
      :error-messages="companyErrors"
      label="car company"
      @input="$v.company.$touch()"
      @blur="$v.company.$touch()"
    ></v-text-field>
    <v-text-field
      v-model="model"
      :error-messages="modelErrors"
      :counter="10"
      label="car model"
      @input="$v.model.$touch()"
      @blur="$v.model.$touch()"
    ></v-text-field>

    <v-btn class="mr-4" @click="submit" color="success"> submit </v-btn>
    <v-btn @click="clear"> clear </v-btn>
  </form>
</template>
<script>
import { validationMixin } from "vuelidate";
import { required, alphaNum, alpha } from "vuelidate/lib/validators";
import carImage from "@/api/carImage.js";
import cars from "@/api/cars.js";

export default {
  mixins: [validationMixin],

  validations: {
    carName: { required, alphaNum },
    company: { alpha },
    model: { alphaNum },
  },

  data: () => ({
    carName: "",
    company: "",
    model: "",
  }),

  computed: {
    companyErrors() {
      const errors = [];
      if (!this.$v.company.$dirty) return errors;
      !this.$v.company.alpha &&
        errors.push("car company can only contain letters");
      return errors;
    },
    modelErrors() {
      const errors = [];
      if (!this.$v.model.$dirty) return errors;
      !this.$v.model.alphaNum &&
        errors.push("car model can only contain letters or numbers");
      return errors;
    },
    carNameErrors() {
      const errors = [];
      if (!this.$v.carName.$dirty) return errors;
      !this.$v.carName.alphaNum &&
        errors.push("car name can only contain letters or numbers");
      !this.$v.carName.required && errors.push("car name is required");
      return errors;
    },
  },

  methods: {
    async submit() {
      this.$v.$touch();
      if (!this.$v.$error) {
        try {
          const car = {
            carName: this.carName,
            carCompany: this.company,
            model: this.model,
            image: carImage.getCarSrc(this.company, this.model),
            userId: this.$store.state.user
          };
          await cars.addCar(car);
          this.$emit("closeModal");
        } catch {
          alert("    ");
        }
      }
    },
    clear() {
      this.$v.$reset();
      this.carName = "";
      this.company = "";
      this.model = "";
    },
  },
};
</script>

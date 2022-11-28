<template>
  <v-container style="display: inline-flexbox; padding: 10px">
    <div>
      <v-dialog v-model="dialog">
        <template v-slot:activator="{ on, attrs }">
          <v-btn color="red lighten-2" dark v-bind="attrs" v-on="on">
            Click Me
          </v-btn>
        </template>

        <v-card style="margin-top: 100px">
          <form style="height: 60vh; width: 40vw; margin-left: 30vw">
            <v-text-field
              v-model.trim="carName"
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
        </v-card>
      </v-dialog>
    </div>
    <v-btn color="success" class="white--text mb-10" @click="dialog = true">
      add car
      <v-icon right dark> mdi-car-side </v-icon>
    </v-btn>
    <v-row>
      <CarCard v-for="car in cars" v-bind:key="car.id" :currentCar="car" />
    </v-row>
    <AddCarModal @closeModal="closeModal" v-if="dialog" />
  </v-container>
</template>

<script>
import CarCard from "../components/CarCard.vue";
import AddCarModal from "../components/AddCarModal.vue";
import cars1 from "@/api/cars";
import { validationMixin } from "vuelidate";
import { required, alphaNum, alpha, helpers } from "vuelidate/lib/validators";
import carImage from "@/api/carImage.js";

export default {
  name: "MainPage",
  mixins: [validationMixin],

  validations: {
    carName: {
      alphaSpace: helpers.regex("alphaSpace", /^[a-zA-Z0-9\s]+$/),
      required,
    },
    company: { alpha },
    model: { alphaNum },
  },

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
      !this.$v.carName.alphaSpace &&
        errors.push("car name can only contain letters or numbers");
      !this.$v.carName.required && errors.push("car name is required");

      return errors;
    },
  },

  data: () => ({
    dialog: false,
    cars: [],
    carName: "",
    company: "",
    model: "",
  }),
  async created() {
    this.cars = await cars1.getCars();
  },
  components: { CarCard, AddCarModal },
  methods: {
    closeModal(car) {
      this.dialog = false;
      this.cars.push(car);
    },
    async submit() {
      this.$v.$touch();
      if (!this.$v.$error) {
        try {
          const car = {
            carName: this.carName,
            carCompany: this.company,
            model: this.model,
            image: await carImage.getCarSrc(this.company, this.model),
            userId: this.$store.state.user,
          };
          await cars.addCar(car);
          this.closeModal(car);
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
  // computed: {
  //   async cars() {
  //     let cars = [];
  //     cars = await cars1.getCars();
  //     return cars;
  //   },
  // },
};
</script>

<template>
  <v-container
    style="display: inline-flexbox; padding: 10px; align-items: center"
  >
    <div>
      <v-dialog v-model="dialog" width="600">
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            class="mb-10"
            variant="text"
            color="lime lighten-1"
            @click="dialog = true"
            v-bind="attrs"
            v-on="on"
          >
            add car
            <v-icon right dark> mdi-car-side </v-icon>
          </v-btn>
        </template>

        <v-card style="color: azure">
          <form
            style="
              height: 55vh;
              margin-left: 20px;
              text-align: center;
              align-items: center;
              padding-top: 15px;
            "
          >
            <h1></h1>
            <v-text-field
              style="width: 30vw; margin-left: 8vw"
              v-model.trim="carName"
              :error-messages="carNameErrors"
              label="car name"
              required
              @input="$v.carName.$touch()"
              @blur="$v.carName.$touch()"
            ></v-text-field>
            <v-text-field
              style="width: 30vw; margin-left: 8vw"
              v-model="company"
              :error-messages="companyErrors"
              label="car company"
              @input="$v.company.$touch()"
              @blur="$v.company.$touch()"
            ></v-text-field>
            <v-text-field
              style="width: 30vw; margin-left: 8vw"
              v-model="model"
              :error-messages="modelErrors"
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

    <v-row>
      <CarCard
        v-for="car in cars"
        v-bind:key="car.id"
        :car="car"
        @enterCar="(n) => goToTreatment(n)"
      />
    </v-row>
  </v-container>
</template>

<script>
import CarCard from "../components/CarCard.vue";
import cars1 from "@/api/cars";
import { validationMixin } from "vuelidate";
import { required, helpers } from "vuelidate/lib/validators";
import carImage from "@/api/carImage.js";

export default {
  name: "MainPage",
  mixins: [validationMixin],

  validations: {
    carName: {
      alphaSpace: helpers.regex("alphaSpace", /^[a-zA-Z0-9\s]+$/),
      required,
    },
    company: { alphaSpace2: helpers.regex("alphaSpace", /^[a-zA-Z0-9\s-]*$/) },
    model: { alphaSpace1: helpers.regex("alphaSpace", /^[a-zA-Z0-9\s-]*$/) },
  },

  computed: {
    companyErrors() {
      const errors = [];
      if (!this.$v.company.$dirty) return errors;
      !this.$v.company.alphaSpace2 &&
        errors.push("car company can only contain letters");
      return errors;
    },
    modelErrors() {
      const errors = [];
      if (!this.$v.model.$dirty) return errors;
      !this.$v.model.alphaSpace1 &&
        errors.push("car name can only contain letters or numbers");

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
  components: { CarCard },
  methods: {
    async submit() {
      this.$v.$touch();
      if (!this.$v.$error) {
        const img = await carImage.getCarSrc(this.company, this.model);
        try {
          const car = {
            carName: this.carName,
            carCompany: this.company,
            model: this.model,
            image: img,
            userId: this.$store.state.user,
          };
          await cars1.addCar(car);
          this.cars.push(car);
          this.clear();
          this.dialog = false;
        } catch {
          alert("we have not managed to add your car, sorry");
        }
      }
    },
    clear() {
      this.$v.$reset();
      this.carName = "";
      this.company = "";
      this.model = "";
    },
    goToTreatment(car) {
      this.$store.commit("changeCurrentCar", car);
      this.$router.push({ name: "treatment" });
    },
  },
};
</script>

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
            large
          >
            add car
            <v-icon right dark normal> mdi-car-side </v-icon>
          </v-btn>
        </template>

        <v-card style="color: azure">
          <form
            style="
              height: 75vh;
              margin-left: 20px;
              text-align: center;
              align-items: center;
              padding-top: 15px;
            "
          >
            <v-text-field
              style="width: 30vw; margin-left: 8vw"
              v-model.trim="carName"
              :error-messages="carNameErrors"
              label="Car Name"
              required
              @input="$v.carName.$touch()"
              @blur="$v.carName.$touch()"
            ></v-text-field>
            <v-select
              style="width: 30vw; margin-left: 8vw"
              v-model="company"
              :items="companies"
              label="Car Company"
              @change="asignModels"
            ></v-select>
            <v-select
              style="width: 30vw; margin-left: 8vw"
              v-model="model"
              :items="models"
              label="Car Model"
              @change="asignSubModels"
            ></v-select>
            <v-select
              style="width: 30vw; margin-left: 8vw"
              v-model="subModel"
              :items="subModels"
              label="Sub Model"
            ></v-select>
            <v-select
              :items="items"
              label="Body Type"
              style="width: 30vw; margin-left: 8vw"
              required
              v-model="type"
            ></v-select>

            <v-btn class="mr-3" @click="submit" color="success">
              add car
            </v-btn>
            <v-btn @click="close" color="red lighten-1" class="mr-3">
              close
            </v-btn>
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
import { required } from "vuelidate/lib/validators";
import carImage from "@/api/carImage.js";
import "vue2-datepicker/index.css";
import Swal from "sweetalert2";

export default {
  name: "MainPage",
  mixins: [validationMixin],

  validations: {
    carName: {
      required,
    },
  },

  computed: {
    carNameErrors() {
      const errors = [];
      if (!this.$v.carName.$dirty) return errors;
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
    subModel: "",
    type: "",
    items: [
      "hatchback",
      "sedan",
      "estate",
      "mpv",
      "suv",
      "convertible",
      "coupe",
      "targa",
      "pickup",
      "closed-cabin",
      "double-cabin",
      "passenger-cabin",
    ],
    companies: [],
    models: [],
    subModels: [],
  }),
  async created() {
    if (!(JSON.parse(localStorage.getItem("access_token")) === null)) {
      try {
        this.cars = await cars1.getCars();
      } catch (error) {
        Swal.fire({
          icon: "error",
          title: "ooops.....",
          text: "we ran into problem, sorry",
        });
      }
    }
  },
  async mounted() {
    this.companies = await carImage.getCompanies();
  },
  components: { CarCard },
  methods: {
    async submit() {
      this.$v.$touch();
      if (!this.$v.$error) {
        const img = await carImage.getCarSrc(
          this.company,
          this.model,
          this.subModel,
          this.type
        );
        try {
          const car = {
            carName: this.carName,
            carCompany: this.company,
            model: this.subModel.includes(this.model)
              ? this.subModel
              : this.model + " " + this.subModel,
            image: img,
            userId: this.$store.state.user.id,
          };
          await cars1.addCar(car);
          this.cars = await cars1.getCars();
          this.clear();
          this.dialog = false;
          this.$alertify.success("car added");
        } catch {
          Swal.fire({
            icon: "error",
            title: "ooops.....",
            text: "we have not managed to add your car, sorry",
          });
        }
      }
    },
    clear() {
      this.$v.$reset();
      this.carName = "";
      this.company = "";
      this.model = "";
      this.subModel = "";
      this.type = "";
    },
    goToTreatment(car) {
      this.$store.commit("changeCurrentCar", car.carId);
      localStorage.setItem("carId", JSON.stringify(car.carId));
      this.$router.push({ name: "treatment" });
      window.scrollTo(0, 0);
    },
    close() {
      this.clear();
      this.dialog = false;
    },
    async asignModels() {
      this.subModel = "";
      this.models = await carImage.getModels(this.company);
      if (this.models.length === 1) {
        this.model = this.models[0];
        this.asignSubModels();
      }
    },
    async asignSubModels() {
      this.subModels = await carImage.getSubModels(this.company, this.model);
      if (this.subModels.length === 1) {
        this.subModel = this.subModels[0];
      }
    },
  },
};
</script>

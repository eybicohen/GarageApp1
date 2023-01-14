<template>
  <v-col
    ><v-card
      class="mx-auto"
      max-width="344"
      style="cursor: pointer"
      @click="$emit('enterCar', car)"
    >
      <v-tooltip top color="success" v-if="allDone">
        <template v-slot:activator="{ on, attrs }">
          <v-icon color="success" v-bind="attrs" v-on="on" right
            >mdi-check-circle</v-icon
          >
        </template>
        <span>this car is all good</span>
      </v-tooltip>
      <v-tooltip top color="warning" v-if="!allDone">
        <template v-slot:activator="{ on, attrs }">
          <v-icon color="warning" v-bind="attrs" v-on="on" right
            >mdi-alert-circle-check</v-icon
          >
        </template>
        <span>this car needs treatment</span>
      </v-tooltip>

      <v-img :src="car.image" height="200px"
        ><template v-slot:placeholder>
          <v-row class="fill-height ma-0" align="center" justify="center">
            <v-progress-circular
              indeterminate
              :size="50"
              color="primary"
            ></v-progress-circular>
          </v-row>
        </template>
      </v-img>

      <v-card-title> {{ car.carCompany }} {{ car.model }} </v-card-title>
      <v-card-subtitle>{{ car.carName }}</v-card-subtitle>
    </v-card></v-col
  >
</template>
<script>
import cars from "@/api/cars";

export default {
  data: () => ({
    show: false,
    allDone: true,
  }),
  props: {
    car: Object,
  },
  async created() {
    this.allDone = await cars.alDone(this.car.carId);
  },

  methods: {},
};
</script>

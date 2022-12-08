<template>
  <v-col
    ><v-card
      class="mx-auto"
      max-width="344"
      style="cursor: pointer"
      @click="$emit('enterCar', car)"
    >
      <v-img :src="car.image" height="200px"></v-img>

      <v-card-title> {{ car.carName }}</v-card-title>
      <v-card-subtitle>{{ car.carCompany }} {{ car.model }} </v-card-subtitle>
    </v-card></v-col
  >
</template>
<script>
export default {
  data: () => ({
    show: false,
  }),
  props: {
    car: Object,
  },
  methods: {},
};
</script>

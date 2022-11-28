<template>
  <v-col
    ><v-card class="mx-auto" max-width="344">
      <v-img :src="currentCar.image" height="200px"></v-img>

      <v-card-title> {{ currentCar.carName }}</v-card-title>

      <v-card-subtitle
        >{{ currentCar.carCompany }} {{ currentCar.model }}
      </v-card-subtitle>

      <v-card-actions>
        <v-btn color="orange lighten-2" text> Explore </v-btn>

        <v-spacer></v-spacer>
      </v-card-actions> </v-card
  ></v-col>
</template>
<script>
export default {
  data: () => ({
    show: false,
  }),
  props: {
    currentCar: Object,
  },
};
</script>

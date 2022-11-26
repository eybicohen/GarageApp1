<template>
  <v-container style="display: inline-flexbox; padding: 10px">
    <v-btn color="success" class="white--text mb-10" @click="dialog = true">
      add car
      <v-icon right dark> mdi-car-side </v-icon>
    </v-btn>
    <v-row>
      <v-col class="col-4"><CarCard /></v-col>
      <v-col class="col-4"><CarCard /></v-col>
      <v-col class="col-4"><CarCard /></v-col>
    </v-row>
    <AddCarModal @closeModal="closeModal" v-if="dialog" />
  </v-container>
</template>

<script>
import CarCard from "../components/CarCard.vue";
import AddCarModal from "../components/AddCarModal.vue";

export default {
  name: "MainPage",
  data: () => ({
    dialog: false,
  }),
  components: { CarCard, AddCarModal },
  methods: {
    closeModal() {
      this.dialog = false;
    },
  },
};
</script>

<template>
  <v-app style="margin-top: 30vh">
    <v-timeline side="end">
      <v-timeline-item
        v-for="item in items"
        :key="item.id"
        :dot-color="item.color"
      >
        <v-alert :value="true" :color="item.color" :icon="item.icon">
          Lorem ipsum dolor sit amet, no nam oblique veritus. Commune scaevola
          imperdiet nec ut, sed euismod convenire principes at. Est et nobis
          iisque percipit, an vim zril disputando voluptatibus, vix an salutandi
          sententiae.
        </v-alert>
      </v-timeline-item>
    </v-timeline>
    <router-view></router-view>
  </v-app>
</template>

<script>
export default {
  name: "CarView",

  data: () => ({
    items: [
      {
        id: 1,
        color: "info",
        icon: "mdi-information",
      },
      {
        id: 2,
        color: "error",
        icon: "mdi-alert-circle",
      },
    ],
  }),
  components: {},
};
</script>

import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    user: {},
    currentCar: {},
    isUserConnected: false,
  },
  getters: {
    user: (state) => {
      return state.user;
    },
    currentCar: (state) => {
      return state.currentCar;
    },
  },
  mutations: {
    changeUser(state, user) {
      state.user = user;
    },
    changeCurrentCar(state, car) {
      state.currentCar = car;
    },
    changeUserConnected(state, isConnected) {
      state.isUserConnected = isConnected;
    },
  },
  actions: {},
  modules: {},
});

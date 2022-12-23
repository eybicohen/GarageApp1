import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import VueAlertify from "vue-alertify";
import GSignInButton from 'vue-google-signin-button'
Vue.use(GSignInButton)

Vue.use(VueAlertify);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");

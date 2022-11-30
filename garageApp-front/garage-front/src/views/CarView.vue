<template>
  <v-app style="margin-top: 30vh; width: 90vw; margin-left: 5vw">
    <div>
      <v-dialog v-model="dialog" width="750">
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            class="mb-10 ml-10"
            variant="text"
            color="lime lighten-1"
            @click="dialog = true"
            v-bind="attrs"
            v-on="on"
          >
            new treatment
            <v-icon right dark> mdi-garage-variant </v-icon>
          </v-btn>
        </template>

        <v-card style="color: azure">
          <form
            style="
              height: 75vh;
              margin-left: 75px;
              text-align: center;
              align-items: center;
              padding-top: 15px;
            "
          >
            <h1></h1>
            <v-text-field
              style="width: 35vw; margin-left: 7vw"
              v-model="treatmentName"
              :error-messages="treatmentNameErrors"
              label="treatment name"
              required
              @input="$v.treatmentName.$touch()"
              @blur="$v.treatmentName.$touch()"
            ></v-text-field>
            <v-textarea
              style="width: 35vw; margin-left: 7vw"
              prepend-icon="mdi-car-wrench"
              v-model="description"
              label="treatment description"
              rows="3"
            ></v-textarea>

            <v-menu
              ref="menu"
              v-model="menu"
              :close-on-content-click="false"
              :return-value.sync="date"
              transition="scale-transition"
              offset-y
              min-width="auto"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  style="width: 35vw; margin-left: 7vw"
                  v-model="date"
                  label="Picker in menu"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker v-model="date" no-title scrollable>
                <v-spacer></v-spacer>
                <v-btn text color="primary" @click="menu = false">
                  Cancel
                </v-btn>
                <v-btn text color="primary" @click="$refs.menu.save(date)">
                  OK
                </v-btn>
              </v-date-picker>
            </v-menu>

            <v-switch
              style="margin-left: 8vw"
              v-model="switch1"
              :label="label()"
              color="success"
            ></v-switch>

            <v-btn class="mr-3" @click="submit" color="success" variant="text">
              add treatment
              <v-icon right dark> mdi-plus </v-icon>
            </v-btn>
            <v-btn @click="close" color="red lighten-1" class="mr-16">
              close
            </v-btn>
          </form>
        </v-card>
      </v-dialog>
    </div>
    <v-timeline align-top :dense="$vuetify.breakpoint.smAndDown">
      <v-timeline-item
        v-for="(treatment, i) in treatments2"
        :key="i"
        :color="decideColor(treatment)"
        :icon="decideIcon(treatment)"
        fill-dot
      >
        <template v-slot:opposite>
          <span
            :class="`headline font-weight-bold ${decideColor(treatment)}--text`"
            v-text="treatment.treatmentDate"
          ></span>
        </template>
        <v-card :color="decideColor(treatment)" dark>
          <v-card-title class="text-h6">
            {{ treatment.treatmentName }}
          </v-card-title>
          <v-card-text class="white text--primary">
            <p>
              {{ treatment.treatmentDescription }}
            </p>
            <v-btn :color="decideColor(treatment)" class="mx-0" outlined>
              Button
            </v-btn>
            <v-btn
              :color="decideColor(treatment)"
              class="ml-3"
              outlined
              v-if="!treatment.isDone"
              @click="checkDone(treatment)"
            >
              check
              <v-icon right dark>mdi-checkbox-outline </v-icon>
            </v-btn>
          </v-card-text>
        </v-card>
      </v-timeline-item>
    </v-timeline>
    <router-view></router-view>
  </v-app>
</template>

<script>
import treatments1 from "@/api/treatments";
import { validationMixin } from "vuelidate";
import { required } from "vuelidate/lib/validators";

export default {
  name: "CarView",
  mixins: [validationMixin],

  validations: {
    treatmentName: { required },
  },

  data: () => ({
    treatments: [],
    switch1: false,
    treatmentName: "",
    description: "",
    dialog: false,
    date: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
      .toISOString()
      .substr(0, 10),
    menu: false,
  }),
  components: {},
  computed: {
    treatmentNameErrors() {
      const errors = [];
      if (!this.$v.treatmentName.$dirty) return errors;
      !this.$v.treatmentName.required &&
        errors.push("treatment name is required");

      return errors;
    },
    treatments2() {
      return this.treatments.sort(function (a, b) {
        // Turn your strings into dates, and then subtract them
        // to get a value that is either negative, positive, or zero.
        return new Date(a.treatmentDate) - new Date(b.treatmentDate);
      });
    },
  },
  async created() {
    if (JSON.parse(localStorage.getItem("car")) === null) {
      this.$router.push({ name: "home" });
    } else if (Object.keys(this.$store.state.currentCar).length === 0) {
      this.$store.commit(
        "changeCurrentCar",
        JSON.parse(localStorage.getItem("car"))
      );
    }
    this.treatments = await treatments1.getTreatments();
  },
  methods: {
    label() {
      return this.switch1 ? "treatment done" : "to do";
    },
    async submit() {
      this.$v.$touch();
      if (!this.$v.$error) {
        try {
          const treatment = {
            treatmentName: this.treatmentName,
            treatmentDescription: this.description,
            treatmentDate: this.date,
            isDone: this.switch1,
            carId: this.$store.state.currentCar,
          };
          await treatments1.addTreatment(treatment);
          this.treatments.push(treatment);
          this.clear();
          this.dialog = false;
        } catch {
          alert("we have not managed to add this treatment, sorry");
        }
      }
    },
    clear() {
      this.$v.$reset();
      (this.treatmentName = ""),
        (this.description = ""),
        (this.switch1 = false),
        (this.date = new Date(
          Date.now() - new Date().getTimezoneOffset() * 60000
        )
          .toISOString()
          .substr(0, 10));
    },
    close() {
      this.dialog = false;
      this.clear();
    },
    decideColor(treatment) {
      return treatment.isDone ? "success" : "warning";
    },
    decideIcon(treatment) {
      return treatment.isDone
        ? "mdi-check-circle"
        : "mdi-alert-circle-check-outline";
    },
    async checkDone(treatment) {
      try {
        treatment.isDone = true;
        await treatments1.changeTreatmentState(treatment);
      } catch (err) {
        alert("we have not managed to update this treatment, sorry");
      }
    },
  },
};
</script>

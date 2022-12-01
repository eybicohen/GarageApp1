<template>
  <v-app style="margin-top: 30vh; width: 90vw; margin-left: 5vw">
    <v-img max-height="600" :src="car.image"></v-img>
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
              label="Treatment Name"
              required
              @input="$v.treatmentName.$touch()"
              @blur="$v.treatmentName.$touch()"
            ></v-text-field>
            <v-textarea
              style="width: 35vw; margin-left: 7vw"
              prepend-icon="mdi-car-wrench"
              v-model="description"
              label="Treatment Description"
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
                  label="Treatment Date"
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
            <v-btn
              color="red darken-1"
              class="mx-0"
              outlined
              @click="delete1(treatment)"
            >
              Delete
              <v-icon right dark> mdi-delete </v-icon>
            </v-btn>
            <v-btn
              :color="decideColor(treatment)"
              class="ml-3"
              outlined
              v-if="!treatment.isDone"
              @click="checkDone(treatment)"
            >
              Check
              <v-icon right dark>mdi-checkbox-outline </v-icon>
            </v-btn>
          </v-card-text>
        </v-card>
      </v-timeline-item>
    </v-timeline>
    <template>
      <v-row class="fill-height mt-14">
        <v-col>
          <v-sheet height="64">
            <v-toolbar flat>
              <v-btn
                outlined
                class="mr-4"
                color="grey darken-2"
                @click="setToday"
              >
                Today
              </v-btn>
              <v-btn fab text small color="grey darken-2" @click="prev">
                <v-icon small> mdi-chevron-left </v-icon>
              </v-btn>
              <v-btn fab text small color="grey darken-2" @click="next">
                <v-icon small> mdi-chevron-right </v-icon>
              </v-btn>
              <v-toolbar-title v-if="$refs.calendar">
                {{ $refs.calendar.title }}
              </v-toolbar-title>
              <v-spacer></v-spacer>
              <v-menu bottom right>
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    outlined
                    color="grey darken-2"
                    v-bind="attrs"
                    v-on="on"
                  >
                    <span>{{ typeToLabel[type] }}</span>
                    <v-icon right> mdi-menu-down </v-icon>
                  </v-btn>
                </template>
                <v-list>
                  <v-list-item @click="type = 'day'">
                    <v-list-item-title>Day</v-list-item-title>
                  </v-list-item>
                  <v-list-item @click="type = 'week'">
                    <v-list-item-title>Week</v-list-item-title>
                  </v-list-item>
                  <v-list-item @click="type = 'month'">
                    <v-list-item-title>Month</v-list-item-title>
                  </v-list-item>
                  <v-list-item @click="type = '4day'">
                    <v-list-item-title>4 days</v-list-item-title>
                  </v-list-item>
                </v-list>
              </v-menu>
            </v-toolbar>
          </v-sheet>
          <v-sheet height="600">
            <v-calendar
              ref="calendar"
              v-model="focus"
              color="primary"
              :events="events"
              :event-color="getEventColor"
              :type="type"
              @click:event="showEvent"
              @click:more="viewDay"
              @click:date="viewDay"
              @change="updateRange"
            ></v-calendar>
            <v-menu
              v-model="selectedOpen"
              :close-on-content-click="false"
              :activator="selectedElement"
              offset-x
            >
              <v-card color="grey lighten-4" min-width="350px" flat>
                <v-toolbar :color="selectedEvent.color" dark>
                  <v-toolbar-title
                    v-html="selectedEvent.name"
                  ></v-toolbar-title>
                  <v-spacer></v-spacer>
                </v-toolbar>
                <v-card-text>
                  <span v-html="selectedEvent.details"></span>
                </v-card-text>
                <v-card-actions>
                  <v-btn text color="secondary" @click="selectedOpen = false">
                    close
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-menu>
          </v-sheet>
        </v-col>
      </v-row>
    </template>
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
    car: {},
    treatments: [],
    switch1: false,
    treatmentName: "",
    description: "",
    dialog: false,
    date: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
      .toISOString()
      .substr(0, 10),
    menu: false,
    focus: "",
    type: "month",
    typeToLabel: {
      month: "Month",
      week: "Week",
      day: "Day",
      "4day": "4 Days",
    },
    selectedEvent: {},
    selectedElement: null,
    selectedOpen: false,
    events: [],
  }),
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
        return new Date(a.treatmentDate) - new Date(b.treatmentDate);
      });
    },
  },
  mounted() {
    this.$refs.calendar.checkChange();
  },
  async created() {
    if (JSON.parse(localStorage.getItem("car")) === null) {
      this.$router.push({ name: "home" });
    } else {
      this.$store.commit(
        "changeCurrentCar",
        JSON.parse(localStorage.getItem("car"))
      );
      this.car = JSON.parse(localStorage.getItem("car"));
    }
    this.treatments = await treatments1.getTreatments();
    this.updateRange();
  },
  methods: {
    label() {
      return this.switch1 ? "Treatment Done" : "To Do";
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
          this.treatments = await treatments1.getTreatments();
          this.clear();
          this.dialog = false;
          this.updateRange();
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
        this.updateRange();
      } catch {
        alert("we have not managed to update this treatment, sorry");
      }
    },
    async delete1(treatment) {
      try {
        await treatments1.deleteTreatment(treatment);
        this.treatments = await treatments1.getTreatments();
        this.updateRange();
      } catch {
        alert("we ran into problem, sorry");
      }
    },
    viewDay({ date }) {
      this.focus = date;
      this.type = "day";
    },
    getEventColor(event) {
      return event.color;
    },
    setToday() {
      this.focus = "";
    },
    prev() {
      this.$refs.calendar.prev();
    },
    next() {
      this.$refs.calendar.next();
    },
    showEvent({ nativeEvent, event }) {
      const open = () => {
        this.selectedEvent = event;
        this.selectedElement = nativeEvent.target;
        requestAnimationFrame(() =>
          requestAnimationFrame(() => (this.selectedOpen = true))
        );
      };

      if (this.selectedOpen) {
        this.selectedOpen = false;
        requestAnimationFrame(() => requestAnimationFrame(() => open()));
      } else {
        open();
      }

      nativeEvent.stopPropagation();
    },
    updateRange() {
      const events = [];

      for (let i = 0; i < this.treatments.length; i++) {
        events.push({
          name: this.treatments[i].treatmentName,
          start: this.treatments[i].treatmentDate,
          color: this.treatments[i].isDone ? "success" : "warning",
        });
      }

      this.events = events;
    },
  },
};
</script>

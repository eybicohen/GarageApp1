import axios from "axios";
const BASE_URL = "http://localhost:9000/treatments/";

export default {
  async getTreatments() {
    const res = await axios.get(
      BASE_URL + JSON.parse(localStorage.getItem("car")).carId
    );
    return res.data;
  },
  async addTreatment(newTreatment) {
    await axios.post(BASE_URL, newTreatment);
  },
  async changeTreatmentState(treatment) {
    await axios.patch(BASE_URL, treatment);
  },
  async deleteTreatment(treatmentId) {
    await axios.delete(BASE_URL + treatmentId);
  },
};

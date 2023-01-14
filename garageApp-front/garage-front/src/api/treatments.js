import axios from "axios";
const BASE_URL = "http://localhost:9000/treatments/";

export default {
  async getTreatments(id = null) {
    const carId = id === null ? JSON.parse(localStorage.getItem("carId")) : id;
    const res = await axios.get(BASE_URL + carId, {
      headers: {
        Authorization: `Bearer ${JSON.parse(
          localStorage.getItem("access_token")
        )}`,
      },
    });
    return res.data;
  },
  async addTreatment(newTreatment) {
    await axios.post(BASE_URL, newTreatment, {
      headers: {
        Authorization: `Bearer ${JSON.parse(
          localStorage.getItem("access_token")
        )}`,
      },
    });
  },
  async changeTreatmentState(treatmentId) {
    await axios.patch(BASE_URL + treatmentId, undefined, {
      headers: {
        Authorization: `Bearer ${JSON.parse(
          localStorage.getItem("access_token")
        )}`,
      },
    });
  },
  async deleteTreatment(treatmentId) {
    await axios.delete(BASE_URL + treatmentId, {
      headers: {
        Authorization: `Bearer ${JSON.parse(
          localStorage.getItem("access_token")
        )}`,
      },
    });
  },
};

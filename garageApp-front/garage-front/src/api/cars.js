import axios from "axios";
import treatments from "./treatments";
const BASE_URL = "http://localhost:9000/cars/";

export default {
  async getCars() {
    const res = await axios.get(BASE_URL + "user/", {
      headers: {
        Authorization: `Bearer ${JSON.parse(
          localStorage.getItem("access_token")
        )}`,
      },
    });
    return res.data;
  },
  async addCar(newCar) {
    await axios.post(BASE_URL, newCar, {
      headers: {
        Authorization: `Bearer ${JSON.parse(
          localStorage.getItem("access_token")
        )}`,
      },
    });
  },
  async deleteCar(carId) {
    await axios.delete(BASE_URL + "car/" + carId, {
      headers: {
        Authorization: `Bearer ${JSON.parse(
          localStorage.getItem("access_token")
        )}`,
      },
    });
  },
  async getById(carId) {
    const res = await axios.get(BASE_URL + "car/" + carId, {
      headers: {
        Authorization: `Bearer ${JSON.parse(
          localStorage.getItem("access_token")
        )}`,
      },
    });
    return res.data;
  },
  async alDone(carId) {
    const carTreatments = await treatments.getTreatments(carId);
    let done = true;
    carTreatments.forEach((treat) => {
      if (!treat.isDone) {
        done = false;
      }
    });
    return done;
  },
};

import axios from "axios";
const BASE_URL = "http://localhost:9000/cars/";

export default {
  async getCars() {
    const res = await axios.get(
      BASE_URL + "user/" + JSON.parse(localStorage.getItem("user")).id
    );
    return res.data;
  },
  async addCar(newCar) {
    await axios.post(BASE_URL, newCar);
  },
  async deleteCar(carId) {
    await axios.delete(BASE_URL + carId);
  },
};

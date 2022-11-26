import axios from "axios";
const BASE_URL = "http://localhost:9000/cars/";

export default {
  async getByUserId(userId) {
    const res = await axios.get(BASE_URL + "user/" + userId);
    const data = res.data;
    const res1 = [];
    for (let i = 0; i < arr.length; i += 3) {
      const chunk = data.slice(i, i + 3);
      res1.push(chunk);
    }
    return res1;
  },
  async addCar(newCar) {
    await axios.post(BASE_URL, newCar);
  },
};

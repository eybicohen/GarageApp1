import axios from "axios";
const BASE_URL = "http://localhost:9000/users/";

export default {
  async get() {
    const res = await axios.get(BASE_URL);
    return res.data;
  },
  async getUserByEmail(email) {
    try {
      const res = await axios.get(BASE_URL + email);
      return res.data;
    } catch {
      return undefined;
    }
  },
  async registerUser(newUser) {
    
    await axios.post(BASE_URL, newUser);
  },
};

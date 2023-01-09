import axios from "axios";
const BASE_URL = "http://localhost:9000/auth/";

export default {
  async loginUser(loginParams) {
    const res = await axios.post(BASE_URL + "login", loginParams);
    return res.data;
  },
  async registerUser(newUser) {
    const res = await axios.post(BASE_URL + "register", newUser);
    return res.data;
  },
  async googleLogin(googleUser) {
    const res = await axios.post(BASE_URL + "login-google", googleUser);
    return res.data;
  },
};

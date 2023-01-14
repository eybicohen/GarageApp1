import axios from "axios";
const BASE_AUTH_URL = "http://localhost:9000/auth/";
const BASE_USERS_URL = "http://localhost:9000/users/";

export default {
  async loginUser(loginParams) {
    const res = await axios.post(BASE_AUTH_URL + "login", loginParams);
    return res.data;
  },
  async registerUser(newUser) {
    const res = await axios.post(BASE_AUTH_URL + "register", newUser);
    return res.data;
  },
  async googleLogin(googleUser) {
    const res = await axios.post(BASE_AUTH_URL + "login-google", googleUser);
    return res.data;
  },
  async isOnlyGoogleUser() {
    const res = await axios.get(BASE_USERS_URL + "is-only-google-user", {
      headers: {
        Authorization: `Bearer ${JSON.parse(
          localStorage.getItem("access_token")
        )}`,
      },
    });
    return res.data;
  },
  async changePassword(password) {
    await axios.patch(BASE_USERS_URL + "change-password", password, {
      headers: {
        "Content-Type": "text/plain",
        Authorization: `Bearer ${JSON.parse(
          localStorage.getItem("access_token")
        )}`,
      },
    });
  },
};

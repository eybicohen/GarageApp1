import axios from "axios";
const imgUrl = "https://cdn.imagin.studio/getImage?customer=img&make=";
const carListingUrl =
  "https://cdn.imagin.studio/getCarListing?customer=copyright-imaginstudio";

export default {
  async readResponseHeader(imgUrl) {
    let res = await axios.get(imgUrl);
    return res.headers["x-imaginstudio-request-found"];
  },
  async getCarSrc(company, model, subModel, bodyType) {
    const state = await this.readResponseHeader(
      imgUrl + company + "&modelFamily=" + model
    );
    if (state === "false") {
      return "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT3cwzqPQT3uAmuqGeZDa9hHY_YHLZYfAU1Mw&usqp=CAU";
    } else {
      const angleArr = [1, 9, 17, 22, 23, 27, 28, 29, 51];
      const angle = angleArr[this.generateRandom()];
      const colors = await this.getColors(company, model);
      const color = colors[this.generateRandom(colors.length)].toString();
      return (
        imgUrl +
        company +
        "&modelFamily=" +
        model +
        "&modelRange=" +
        subModel +
        "&angle=" +
        angle +
        "&zoomType=fullscreen&paintId=" +
        color +
        "&modelVariant=" +
        bodyType
      );
    }
  },
  generateRandom(maxLimit = 9) {
    let rand = Math.random() * maxLimit;

    rand = Math.floor(rand);

    return rand - 1;
  },
  async getColors(company, model) {
    const url =
      "https://cdn.imagin.studio/getPaints?&customer=img&target=make&make=" +
      company +
      "&modelFamily=" +
      model;
    const res = await axios.get(url);
    return Object.keys(res.data.paintData.paintCombinations);
  },
  async getCompanies() {
    const res = await axios.get(carListingUrl);
    return res.data.make;
  },
  async getModels(company) {
    const res = await axios.get(carListingUrl + "&make=" + company);
    return res.data.modelFamily;
  },
  async getSubModels(company, model) {
    const res = await axios.get(
      carListingUrl + "&make=" + company + "&modelFamily=" + model
    );
    return res.data.modelRange;
  },
};

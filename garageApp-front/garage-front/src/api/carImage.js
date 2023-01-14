import axios from "axios";
const IMG_URL = "https://cdn.imagin.studio/getImage?customer=img&make=";
const CAR_LISTING_URL =
  "https://cdn.imagin.studio/getCarListing?customer=copyright-imaginstudio";

export default {
  async getCarSrc(company, model, subModel, bodyType) {
    const angleArr = [1, 9, 17, 22, 23, 27, 28, 29, 51];
    const angle = angleArr[this.generateRandom()];
    const colors = await this.getColors(company, model);
    const color = colors[this.generateRandom(colors.length)].toString();
    return (
      IMG_URL +
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
    const res = await axios.get(CAR_LISTING_URL);
    return res.data.make;
  },
  async getModels(company) {
    const res = await axios.get(CAR_LISTING_URL + "&make=" + company);
    return res.data.modelFamily;
  },
  async getSubModels(company, model) {
    const res = await axios.get(
      CAR_LISTING_URL + "&make=" + company + "&modelFamily=" + model
    );
    return res.data.modelRange;
  },
};

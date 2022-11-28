import axios from "axios";

export default {
  async getCarSrc(company, model) {
    await axios
      .get(
        "https://cdn.imagin.studio/getImage?customer=img&make=" +
          company +
          "&modelFamily=" +
          model
      )
      .catch(function (error) {
        if (error.response) {
          company = "";
        }
      });
    if (company.length === 0 || model.length === 0) {
      return "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT3cwzqPQT3uAmuqGeZDa9hHY_YHLZYfAU1Mw&usqp=CAU";
    } else {
      return (
        "https://cdn.imagin.studio/getImage?customer=img&make=" +
        company +
        "&modelFamily=" +
        model
      );
    }
  },
};

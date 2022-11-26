export default {
  getCarSrc(company, model) {
    if (company.length === 0 || model.length === 0) {
      return "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAALsAAABkCAMAAADpJrGiAAAAJ1BMVEX////Nzc3j4+PU1NTe3t7n5+fY2NjR0dHy8vLr6+vv7+/4+Pj6+voKTTpkAAADi0lEQVR4nO2agZKrIAxFaxIQtf//vY8EVFSsVLHum8mZ7ax1uun1GiCGfb0URVEURVEURVEURVEU5fcMTQNPazgLNYeAce+nZeYYjqUHbP/H9Pc4i2sRCQAM43/7MxSOcPwQ9k/rnRhgVG2BNS4wrHV+B9SG9HlatOBsNHOrWxClS/BPqH+bUfgu7VY7BPe7J5VHy1vaVx5MtvlrQveU8m6a+j5Cux/hi3pizhnsNK98dB2yCR+xT6T9ZPmx7TLRZJNmvK5fpv2wWkFLfN+9QB6zv0h75wzYZsuB9vbTZ4IPdrhNtIGM4pm9hIjgh0m0nWLcJP2z8kPxtH9zTHr3bplyTF7xglDDQCxjYimzSIsj7ZEQxrlKY6Av0F6Ar9E2HPwJgbs4Eg5z5maoO38BR+78BDiXRE/LnoDv/X9acor9bhV+P613xTcLWXcc7seU129PK81g/lvbPRVsR9O7wXVdrkKrQAjfm+0kXZjy+6GtG0WDu2H9st1YMph+bU7hZL8b26XVCLraK9jiZtKqLils8OyExmEl1hQ09crBdRG19Oaa9tDDk+rQhH4RVBSPck9bMhI+iF98+xXtfWxdkK9Z+QL4XGZMnUVcJwnP30DTuQrarTQogJC/QC5iHf0S8qzlTWl9eOJWIYfHZD64or1rQr4E7dJc4jO15koj0tlu0R6dT6wpk559akIOBayY40rsdfSr0i2IYpKXP8ZxEHyjPddY5yAEbcx3/vH64/kK8F2VkHO+S/jZmkLtuQ0NDs7Df3oIRZ+N3hmqkzQ+fBtzZXzitee0Z56bgjHWWp5l+CWHfLKKdhn9ROy4JKYcYnpbi7VvnRftoyeT+U2lpGGP5yYDTbc2caZc+2tYNcQS7YCUaK9SdFKqHe1F7RHHXT3OPk68MWeY+jmDEhPm8Kx9cqa0fs/SyFidNw2wkeBQRzsv//5GYxI+nWdOtgwS7VZG6VgTBNv7OlWBaCeEaY7kxPTnhr7G/mxc+MIkHIYTD4jrgQPIkyQka5N0XWv1WxuJTm2oZ2JNUG0Pg42nRU3AztRqdMdlW0yRxGwq2v56WRHvlzrW7vNR3leN3oa1I9pSzRemaUKJHUYUDyOsGF2GZSvb7WHur7pvFKoo5JZ42Hyo6sx6ta287fJeTlk1XWfSR8gbthrTNfqGvcAhbBjRTf+XETsnJ9q/iqIoiqIoiqIoiqIoivIF/wA7OSE8q/IcNwAAAABJRU5ErkJggg==";
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

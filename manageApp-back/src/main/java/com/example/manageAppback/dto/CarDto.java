package com.example.manageAppback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CarDto {
    @JsonProperty
    private Integer carId;

    @JsonProperty
    private String carName;

    @JsonProperty
    private String carCompany;

    @JsonProperty
    private String model;

    @JsonProperty
    private String image;

    @JsonProperty
    private Integer userId;

    public CarDto(Integer carId, String carName, String carCompany, String model, String image, Integer userId) {
        this.changeCarId(carId);
        this.changeCarName(carName);
        this.changeCarCompany(carCompany);
        this.changeModel(model);
        this.changeImage(image);
        this.changeUserId(userId);
    }

    private void changeCarId(Integer carId) {
        this.carId = carId;
    }

    private void changeCarName(String carName) {
        this.carName = carName;
    }

    private void changeCarCompany(String carCompany) {
        this.carCompany = carCompany;
    }

    private void changeModel(String model) {
        this.model = model;
    }

    private void changeImage(String image) {
        this.image = image;
    }

    private void changeUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public Integer getCarId() {
        return this.carId;
    }

    public String getCarName() {
        return this.carName;
    }

    public String getCarCompany() {
        return this.carCompany;
    }

    public String getModel() {
        return this.model;
    }

    public String getImage() {
        return this.image;
    }
}

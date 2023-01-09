package com.example.manageAppback.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Integer carId;

    @Column(name = "car_name")
    @JsonProperty
    private String carName;

    @Column(name = "car_company")
    @JsonProperty
    private String carCompany;

    @Column(name = "model")
    @JsonProperty
    private String model;

    @Column(name = "image")
    @JsonProperty
    private String image;

    @JsonProperty
    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = User.class)
    private User userId;

    public Car(String carName, String carCompany, String model, String image, User userId) {
        this.carName = carName;
        this.carCompany = carCompany;
        this.model = model;
        this.image = image;
        this.userId = userId;
    }

    public Car() {
    }

    public Integer getCarId() {
        return carId;
    }

    public String getCarName() {
        return carName;
    }

    public String getCarCompany() {
        return carCompany;
    }

    public String getModel() {
        return model;
    }

    public String getImage() {
        return image;
    }

    public User getUserId() {
        return userId;
    }
}

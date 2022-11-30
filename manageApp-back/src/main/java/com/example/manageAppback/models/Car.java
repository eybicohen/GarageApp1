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

}

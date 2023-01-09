package com.example.manageAppback.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "treatments")
public class Treatment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Integer treatmentId;

    @Column(name = "treatment_name")
    @JsonProperty
    private String treatmentName;

    @Column(name = "treatment_description")
    @JsonProperty
    private String treatmentDescription;

    @Column(name = "treatment_date")
    @JsonProperty
    private LocalDate treatmentDate;

    @Column(name = "is_done")
    @JsonProperty
    private Boolean isDone;

    @JsonProperty
    @JoinColumn(name = "car_id")
    @ManyToOne(targetEntity = Car.class)
    private Car carId;

    public Treatment(Integer treatmentId, String treatmentName,
                     String treatmentDescription, LocalDate treatmentDate, Boolean isDone, Car carId) {
        this.treatmentId = treatmentId;
        this.treatmentName = treatmentName;
        this.treatmentDescription = treatmentDescription;
        this.treatmentDate = treatmentDate;
        this.isDone = isDone;
        this.carId = carId;
    }

    public Treatment() {
    }

    public Integer getTreatmentId() {
        return this.treatmentId;
    }

    public String getTreatmentName() {
        return this.treatmentName;
    }

    public String getTreatmentDescription() {
        return this.treatmentDescription;
    }

    public LocalDate getTreatmentDate() {
        return this.treatmentDate;
    }

    public Boolean getDone() {
        return this.isDone;
    }

    public Car getCarId() {
        return this.carId;
    }

    public void changeDone(Boolean done) {
        this.isDone = done;
    }
}

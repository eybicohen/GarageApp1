package com.example.manageAppback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class TreatmentDto {
    @JsonProperty
    private Integer treatmentId;

    @JsonProperty
    private String treatmentName;

    @JsonProperty
    private String treatmentDescription;

    @JsonProperty
    private LocalDate treatmentDate;

    @JsonProperty
    private Boolean isDone;

    @JsonProperty
    private Integer carId;

    public TreatmentDto(Integer treatmentId, String treatmentName,
                        String treatmentDescription, LocalDate treatmentDate,
                        Boolean isDone, Integer carId) {
        this.changeTreatmentId(treatmentId);
        this.changeTreatmentName(treatmentName);
        this.changeTreatmentDescription(treatmentDescription);
        this.changeTreatmentDate(treatmentDate);
        this.changeDone(isDone);
        this.changeCarId(carId);
    }

    private void changeTreatmentId(Integer treatmentId) {
        this.treatmentId = treatmentId;
    }

    private void changeTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    private void changeTreatmentDescription(String treatmentDescription) {
        this.treatmentDescription = treatmentDescription;
    }

    private void changeTreatmentDate(LocalDate treatmentDate) {
        this.treatmentDate = treatmentDate;
    }

    private void changeDone(Boolean done) {
        isDone = done;
    }

    private void changeCarId(Integer carId) {
        this.carId = carId;
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

    public Integer getCarId() {
        return this.carId;
    }
}

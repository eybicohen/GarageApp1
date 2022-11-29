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

    @JsonProperty
    @JoinColumn(name = "car_id")
    @ManyToOne(targetEntity = Car.class)
    private Car carId;

    public LocalDate getDate() {
        return this.treatmentDate;
    }
}

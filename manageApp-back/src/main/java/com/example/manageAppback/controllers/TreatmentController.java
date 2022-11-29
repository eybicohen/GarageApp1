package com.example.manageAppback.controllers;

import com.example.manageAppback.models.Car;
import com.example.manageAppback.models.Treatment;
import com.example.manageAppback.services.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treatments")
@CrossOrigin
public class TreatmentController {
    @Autowired
    TreatmentService treatmentService;

    @GetMapping("/user/{carId}")
    public List<Car> getAllByUserId(@PathVariable Integer carId) {
        return this.treatmentService.getAllByCarId(carId);
    }

    @PostMapping("")
    public void addCar(@RequestBody Treatment newTreatment) {
        this.treatmentService.addTreatment(newTreatment);
    }

}

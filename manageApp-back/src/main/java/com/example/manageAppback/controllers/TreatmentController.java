package com.example.manageAppback.controllers;

import com.example.manageAppback.dto.TreatmentDto;
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

    @GetMapping("/{carId}")
    public List<TreatmentDto> getAllByCarId(@PathVariable Integer carId) {
        return this.treatmentService.getAllByCarId(carId);
    }

    @PostMapping("")
    public void addTreatment(@RequestBody TreatmentDto newTreatment) {
        this.treatmentService.addTreatment(newTreatment);
    }

    @PatchMapping("/{id}")
    public void changeState(@PathVariable Integer id) {
        this.treatmentService.changeState(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTreatment(@PathVariable Integer id) {
        this.treatmentService.delete(id);
    }
}

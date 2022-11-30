package com.example.manageAppback.services;

import com.example.manageAppback.models.Car;
import com.example.manageAppback.models.Treatment;
import com.example.manageAppback.repositories.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentService {
    @Autowired
    TreatmentRepository treatmentRepository;

    public List<Treatment> getAllByCarId(Integer carId) {
        return this.treatmentRepository.findByCarId_CarId(carId);
    }

    public void addTreatment(Treatment newTreatment) {
        this.treatmentRepository.save(newTreatment);
    }

    public void changeState(Treatment treatment) {
        this.treatmentRepository.save(treatment);
    }
}

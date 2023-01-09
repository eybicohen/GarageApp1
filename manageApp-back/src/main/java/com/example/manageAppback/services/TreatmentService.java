package com.example.manageAppback.services;

import com.example.manageAppback.dto.TreatmentDto;
import com.example.manageAppback.models.Car;
import com.example.manageAppback.models.Treatment;
import com.example.manageAppback.repositories.CarRepository;
import com.example.manageAppback.repositories.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TreatmentService {
    @Autowired
    TreatmentRepository treatmentRepository;
    @Autowired
    CarRepository carRepository;

    public List<TreatmentDto> getAllByCarId(Integer carId) {
        List<Treatment> treatmentList = this.treatmentRepository.findByCarId_CarId(carId);
        List<TreatmentDto> treatmentDtos = new ArrayList<>();
        treatmentList.forEach(t -> treatmentDtos.add(new TreatmentDto(t.getTreatmentId(),
                t.getTreatmentName(), t.getTreatmentDescription(), t.getTreatmentDate(),
                t.getDone(), t.getCarId().getCarId())));

        return treatmentDtos;
    }

    public void addTreatment(TreatmentDto newTreatment) {
        Car car = this.carRepository.findById(newTreatment.getCarId()).get();
        Treatment treatment = new Treatment(newTreatment.getTreatmentId(), newTreatment.getTreatmentName(),
                newTreatment.getTreatmentDescription(), newTreatment.getTreatmentDate(), newTreatment.getDone(), car);
        this.treatmentRepository.save(treatment);
    }

    public void changeState(Integer id) {
        Treatment treatment = treatmentRepository.findById(id).get();
        treatment.changeDone(true);
        this.treatmentRepository.save(treatment);
    }

    public void delete(Integer id) {
        this.treatmentRepository.deleteById(id);
    }
}

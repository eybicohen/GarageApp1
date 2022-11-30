package com.example.manageAppback.repositories;

import com.example.manageAppback.models.Car;
import com.example.manageAppback.models.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {

    List<Treatment> findByCarId_CarId(Integer carId);
}

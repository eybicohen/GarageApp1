package com.example.manageAppback.services;

import com.example.manageAppback.models.Car;
import com.example.manageAppback.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAll() {
        return this.carRepository.findAll();
    }

    public Car getById(Integer id) {
        return this.carRepository.findById(id).get();
    }

    public List<Car> getAllByUserId(Integer userId) {
        return this.carRepository.findByUserId_Id(userId);
    }


    public void addCar(Car newCar) {
        this.carRepository.save(newCar);
    }

    public void deleteCar(Integer id) {
        this.carRepository.deleteById(id);
    }
}

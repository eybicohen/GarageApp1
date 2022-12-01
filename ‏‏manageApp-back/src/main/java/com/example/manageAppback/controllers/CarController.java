package com.example.manageAppback.controllers;

import com.example.manageAppback.models.Car;
import com.example.manageAppback.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@CrossOrigin
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("")
    public List<Car> getAll() {
        return this.carService.getAll();
    }

    @GetMapping("/{id}")
    public Car getById(@PathVariable Integer id) {
        return this.carService.getById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Car> getAllByUserId(@PathVariable Integer userId) {
        return this.carService.getAllByUserId(userId);
    }

    @PostMapping("")
    public void addCar(@RequestBody Car newCar) {
        this.carService.addCar(newCar);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Integer id) {
        this.carService.deleteCar(id);
    }
}

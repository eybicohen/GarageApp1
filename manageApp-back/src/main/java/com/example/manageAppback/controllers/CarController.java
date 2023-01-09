package com.example.manageAppback.controllers;

import com.example.manageAppback.config.JwtUtils;
import com.example.manageAppback.dto.CarDto;
import com.example.manageAppback.models.Car;
import com.example.manageAppback.models.User;
import com.example.manageAppback.services.CarService;
import com.example.manageAppback.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {
    private final JwtUtils jwtUtils;

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<Car> getAll() {
        return this.carService.getAll();
    }

    @GetMapping("/car/{id}")
    public CarDto getById(@PathVariable Integer id) {
        return this.carService.getById(id);
    }

    @GetMapping("/user")
    public List<CarDto> getAllByUserId(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        User user = this.userService.getUserByToken(token);
        return this.carService.getAllByUserId(user.getId());
    }

    @PostMapping("")
    public void addCar(@RequestBody CarDto newCar, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        User user = this.userService.getUserByToken(token);
        this.carService.addCar(newCar, user);
    }

    @DeleteMapping("/car/{id}")
    public void deleteCar(@PathVariable Integer id) {
        this.carService.deleteCar(id);
    }
}

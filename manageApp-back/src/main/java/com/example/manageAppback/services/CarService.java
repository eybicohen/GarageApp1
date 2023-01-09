package com.example.manageAppback.services;

import com.example.manageAppback.dto.CarDto;
import com.example.manageAppback.models.Car;
import com.example.manageAppback.models.User;
import com.example.manageAppback.repositories.CarRepository;
import com.example.manageAppback.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Car> getAll() {
        return this.carRepository.findAll();
    }

    public CarDto getById(Integer id) {
        Car car = this.carRepository.findById(id).get();
        CarDto carDto = new CarDto(car.getCarId(), car.getCarName(),
                car.getCarCompany(), car.getModel(), car.getImage(), car.getUserId().getId());

        return carDto;
    }

    public List<CarDto> getAllByUserId(Integer userId) {
        List<Car> cars = this.carRepository.findByUserId_Id(userId);
        List<CarDto> carsDto = new ArrayList<>();
        cars.forEach(c -> carsDto.add(new CarDto(c.getCarId(), c.getCarName(), c.getCarCompany(),
                c.getModel(), c.getImage(), c.getUserId().getId())));

        return carsDto;
    }

    public void addCar(CarDto newCar,User user) {
        Car car = new Car(newCar.getCarName(),
                newCar.getCarCompany(), newCar.getModel(), newCar.getImage(), user);
        this.carRepository.save(car);
    }

    public void deleteCar(Integer id) {
        this.carRepository.deleteById(id);
    }
}

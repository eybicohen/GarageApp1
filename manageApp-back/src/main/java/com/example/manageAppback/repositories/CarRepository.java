package com.example.manageAppback.repositories;


import com.example.manageAppback.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> findByUserId_Id(Integer userId);
}

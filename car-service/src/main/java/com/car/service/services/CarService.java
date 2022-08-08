package com.car.service.services;

import com.car.service.entities.Car;
import com.car.service.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAll(){
        return carRepository.findAll();
    }

    public Car get(Integer carId){
        return carRepository.findById(carId).orElse(null);
    }

    public Car save(Car newUser){
        return carRepository.save(newUser);
    }

    public List<Car> getByUserId(Integer userId){
        return carRepository.findByUserId(userId);
    }

}

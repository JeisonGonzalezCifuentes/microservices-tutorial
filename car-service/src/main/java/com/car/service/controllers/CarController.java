package com.car.service.controllers;

import com.car.service.entities.Car;
import com.car.service.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> geAll(){
        List<Car> allCars = carService.getAll();

        return allCars.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(allCars);
    }

    @GetMapping("/{carId}")
    public ResponseEntity<Car> get(@PathVariable("carId") Integer carId){
        Car car = carService.get(carId);

        return car == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(car);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Car>> getByUserId(@PathVariable("userId") Integer userId){
        List<Car> allCarsByUser = carService.getByUserId(userId);

        return allCarsByUser.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(allCarsByUser);
    }

    @PostMapping
    public ResponseEntity<Car> save(@RequestBody Car newCar){
        Car carSaved = carService.save(newCar);
        return ResponseEntity.ok(carSaved);
    }

}

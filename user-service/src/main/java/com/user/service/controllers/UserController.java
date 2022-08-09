package com.user.service.controllers;

import com.user.service.entities.User;
import com.user.service.models.Bike;
import com.user.service.models.Car;
import com.user.service.models.Vehicle;
import com.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> geAll(){
        List<User> allUsers = userService.getAll();

        return allUsers.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> get(@PathVariable("userId") Integer userId){
        User user = userService.get(userId);

        return user == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User newUser){
        User userSaved = userService.save(newUser);
        return ResponseEntity.ok(userSaved);
    }

    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallBackSaveVehicle")
    @PostMapping("/{userId}/car")
    public ResponseEntity<Car> saveCar(@PathVariable("userId") Integer userId, @RequestBody Car car){
        Car savedCar = userService.saveCar(userId, car);
        return ResponseEntity.ok(savedCar);
    }

    @CircuitBreaker(name = "bikesCB", fallbackMethod = "fallBackSaveVehicle")
    @PostMapping("/{userId}/bike")
    public ResponseEntity<Bike> saveBike(@PathVariable("userId") Integer userId, @RequestBody Bike bike){
        Bike savedBike = userService.saveBike(userId, bike);
        return ResponseEntity.ok(savedBike);
    }

    @CircuitBreaker(name = "allCB", fallbackMethod = "fallBackGetVehicle")
    @GetMapping("/{userId}/vehicles")
    public ResponseEntity<Map<String, Object>> getUserAndVehicles(@PathVariable("userId") Integer userId){
        Map<String, Object> userAndVehicles = userService.getUserAndVehicles(userId);
        return ResponseEntity.ok(userAndVehicles);
    }

    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallBackGetVehicle")
    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCarsByUser(@PathVariable("userId") Integer userId){
        User user = userService.get(userId);
        if (user == null){
            return ResponseEntity.notFound().build();
        }

        List<Car> carsByUser = userService.getAllCars(userId);
        return ResponseEntity.ok(carsByUser);
    }

    @CircuitBreaker(name = "bikesCB", fallbackMethod = "fallBackGetVehicle")
    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikesByUser(@PathVariable("userId") Integer userId){
        User user = userService.get(userId);
        if (user == null){
            return ResponseEntity.notFound().build();
        }

        List<Bike> bikesByUser = userService.getAllBikes(userId);
        return ResponseEntity.ok(bikesByUser);
    }

    private ResponseEntity<List<Vehicle>> fallBackGetVehicle(@PathVariable("userId") Integer userId,
                                                             RuntimeException runtimeException){
        return new ResponseEntity("User: " + userId + " does not have available vehicles.", HttpStatus.OK);
    }

    private ResponseEntity<List<Vehicle>> fallBackSaveVehicle(@PathVariable("userId") Integer userId,
                                                             @RequestBody Vehicle vehicle,
                                                             RuntimeException runtimeException){
        return new ResponseEntity("User: " + userId + " can't buy vehicles.", HttpStatus.OK);
    }

}

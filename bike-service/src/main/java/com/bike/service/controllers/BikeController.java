package com.bike.service.controllers;

import com.bike.service.entities.Bike;
import com.bike.service.services.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {

    @Autowired
    private BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAll(){
        List<Bike> allBikes = bikeService.getAll();

        return allBikes.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(allBikes);
    }

    @GetMapping("/{bikeId}")
    public ResponseEntity<Bike> get(@PathVariable("bikeId") Integer carId){
        Bike bike = bikeService.get(carId);

        return bike == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(bike);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Bike>> getByUserId(@PathVariable("userId") Integer userId){
        List<Bike> allBikesByUser = bikeService.getByUserId(userId);

        return allBikesByUser.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(allBikesByUser);
    }

    @PostMapping
    public ResponseEntity<Bike> save(@RequestBody Bike newCar){
        Bike carSaved = bikeService.save(newCar);
        return ResponseEntity.ok(carSaved);
    }

}

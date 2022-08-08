package com.bike.service.services;

import com.bike.service.entities.Bike;
import com.bike.service.repositories.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

    @Autowired
    private BikeRepository bikeRepository;

    public List<Bike> getAll(){
        return bikeRepository.findAll();
    }

    public Bike get(Integer bikeId){
        return bikeRepository.findById(bikeId).orElse(null);
    }

    public Bike save(Bike newUser){
        return bikeRepository.save(newUser);
    }

    public List<Bike> getByUserId(Integer userId){
        return bikeRepository.findByUserId(userId);
    }
}

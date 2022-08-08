package com.user.service.services;

import com.user.service.entities.User;
import com.user.service.feign.clients.BikeFeignClient;
import com.user.service.feign.clients.CarFeignClient;
import com.user.service.models.Bike;
import com.user.service.models.Car;
import com.user.service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarFeignClient carFeignClient;
    @Autowired
    private BikeFeignClient bikeFeignClient;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public List<Car> getAllCars(Integer userId){
        List allCarsByUser = restTemplate.getForObject("http://localhost:8082/car/user/" + userId, List.class);
        return allCarsByUser;
    }
    public Car saveCar(Integer userId, Car car){
        car.setUserId(userId);
        Car savedCar = carFeignClient.save(car);
        return savedCar;
    }

    public Bike saveBike(Integer userId, Bike bike){
        bike.setUserId(userId);
        Bike savedBike = bikeFeignClient.save(bike);
        return savedBike;
    }

    public List<Bike> getAllBikes(Integer userId){
        List allBikesByUser = restTemplate.getForObject("http://localhost:8085/bike/user/" + userId, List.class);
        return allBikesByUser;
    }

    public User get(Integer userId){
        return userRepository.findById(userId).orElse(null);
    }

    public User save(User newUser){
        return userRepository.save(newUser);
    }

    public Map<String, Object> getUserAndVehicles(Integer userId){
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);

        if (user == null){
            result.put("Message", "User does not exist");
            return result;
        }

        result.put("User", user);
        List<Car> cars = carFeignClient.getCars(userId);
        if (cars.isEmpty()){
            result.put("Cars", "User does not have cars");
        } else {
            result.put("Cars", cars);
        }

        List<Bike> bikes = bikeFeignClient.getBikes(userId);
        if (bikes.isEmpty()){
            result.put("Bikes", "User does not have cars");
        } else {
            result.put("Bikes", cars);
        }

        return result;
    }
}

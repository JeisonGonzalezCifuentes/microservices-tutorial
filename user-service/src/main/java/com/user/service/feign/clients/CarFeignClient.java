package com.user.service.feign.clients;

import com.user.service.models.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "car-service")
public interface CarFeignClient {

    @PostMapping()
    public Car save(@RequestBody Car car);

    @GetMapping("/user/{userId}")
    public List<Car> getCars(@PathVariable("userId") Integer userId);

}

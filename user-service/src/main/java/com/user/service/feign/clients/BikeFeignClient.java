package com.user.service.feign.clients;

import com.user.service.models.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "bike-service", path = "/bike")
public interface BikeFeignClient {

    @PostMapping
    public Bike save(@RequestBody Bike bike);

    @GetMapping("/user/{userId}")
    public List<Bike> getBikes(@PathVariable("userId") Integer userId);


}

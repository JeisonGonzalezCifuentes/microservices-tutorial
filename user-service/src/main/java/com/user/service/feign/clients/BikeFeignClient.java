package com.user.service.feign.clients;

import com.user.service.models.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "bike-service")
public interface BikeFeignClient {

    @PostMapping
    public Bike save(@RequestBody Bike bike);

    @GetMapping("/user/{userId}")
    public List<Bike> getBikes(@PathVariable("userId") Integer userId);


}

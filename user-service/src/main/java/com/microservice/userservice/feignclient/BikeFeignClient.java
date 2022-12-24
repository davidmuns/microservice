package com.microservice.userservice.feignclient;

import com.microservice.userservice.model.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "bike-service", url = "http://localhost:8003")
@RequestMapping("/bike")
public interface BikeFeignClient {
    @PostMapping("")
    Bike newBike(@RequestBody Bike bike);

    @GetMapping("/all/{userId}")
    List<Bike> getBikes(@PathVariable("userId") Integer userId);
}

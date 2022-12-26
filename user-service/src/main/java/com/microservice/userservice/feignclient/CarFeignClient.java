package com.microservice.userservice.feignclient;

import com.microservice.userservice.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "car-service")
@RequestMapping("/car")
public interface CarFeignClient {

    @PostMapping("")
    Car newCar(@RequestBody Car car);

    @GetMapping("/all/{userId}")
    List<Car>getCars(@PathVariable("userId") Integer userId);
}

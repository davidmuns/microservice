package com.microservice.carservice.controller;

import com.microservice.carservice.entity.Car;
import com.microservice.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("/all")
    public ResponseEntity<List<Car>> getCars(){
        List<Car> cars =  carService.getCars();
        if(cars.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable("id") Integer id){
        Car car = carService.getCar(id);
        if(car == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @PostMapping("")
    public ResponseEntity<Car> newCar(@RequestBody Car car){
        Car newCar = car;
        carService.saveCar(newCar);
        return ResponseEntity.ok(newCar);
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<Car>> getCarsByUserId(@PathVariable("userId") Integer userId){
        List<Car> cars =  carService.getCarsByUserId(userId);
        if(cars.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

}

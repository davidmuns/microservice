package com.microservice.userservice.controller;

import com.microservice.userservice.entity.User;
import com.microservice.userservice.model.Bike;
import com.microservice.userservice.model.Car;
import com.microservice.userservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers(){
       List<User> users =  userService.getUsers();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
       return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id){
        User user = userService.getUser(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("")
    public ResponseEntity<User> newUser(@RequestBody User user){
        User newUser = user;
        userService.saveUser(newUser);
        return ResponseEntity.ok(newUser);
    }
    @GetMapping("/car/all/{userId}")
    public ResponseEntity<List<Car>> getCars(@PathVariable("userId") Integer userId){
        User user = userService.getUser(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        List<Car> cars = userService.getCars(userId);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/bike/all/{userId}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") Integer userId){
        User user = userService.getUser(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        List<Bike> bikes = userService.getBikes(userId);
        return ResponseEntity.ok(bikes);
    }
    @GetMapping("/vehicle/all/{userId}")
    public ResponseEntity<HashMap<String, Object>> getCarsAndBikes(@PathVariable("userId") Integer userId) {
        User user = userService.getUser(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }

        HashMap<String, Object> vehicles = userService.getCarsAndBikes(userId);
        if(vehicles == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vehicles);
    }
    @PostMapping("/buycar/{userId}")
    public ResponseEntity<Car> newCar(@RequestBody Car car, @PathVariable("userId") Integer userId){
        User user = userService.getUser(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        Car newCar = userService.saveCar(userId, car);
        return ResponseEntity.ok(newCar);
    }
    @PostMapping("/buybike/{userId}")
    public ResponseEntity<Bike> newBike(@PathVariable("userId") Integer userId, @RequestBody Bike bike){
        User user = userService.getUser(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        Bike newBike = userService.saveBike(userId, bike);
        return ResponseEntity.ok(newBike);
    }


}

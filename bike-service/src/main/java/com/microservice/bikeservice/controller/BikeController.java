package com.microservice.bikeservice.controller;

import com.microservice.bikeservice.entity.Bike;
import com.microservice.bikeservice.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {

    @Autowired
    BikeService bikeService;

    @GetMapping("/all")
    public ResponseEntity<List<Bike>> getBikes(){
        List<Bike> bikes =  bikeService.getBikes();
        if(bikes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bikes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getBike(@PathVariable("id") Integer id){
        Bike bike = bikeService.getBike(id);
        if(bike == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bike);
    }

    @PostMapping("")
    public ResponseEntity<Bike> newBike(@RequestBody Bike bike){
        Bike newBike = bike;
        bikeService.saveBike(newBike);
        return ResponseEntity.ok(newBike);
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<Bike>> getBikesByUserId(@PathVariable("userId") Integer userId){
        List<Bike> bikes =  bikeService.getBikesByUserId(userId);
        if(bikes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bikes);
    }

}

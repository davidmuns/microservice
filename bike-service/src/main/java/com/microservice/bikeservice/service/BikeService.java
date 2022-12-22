package com.microservice.bikeservice.service;

import com.microservice.bikeservice.entity.Bike;
import com.microservice.bikeservice.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

    @Autowired
    BikeRepository bikeRepository;

    public List<Bike> getBikes() {
        return bikeRepository.findAll();
    }

    public Bike getBike(Integer id) {
        return bikeRepository.findById(id).orElse(null);
    }

    public Bike saveBike(Bike bike) {
        return bikeRepository.save(bike);
    }

    public List<Bike> getBikesByUserId(Integer userId){
        return bikeRepository.findByUserId(userId);
    }
}

package com.microservice.carservice.service;

import com.microservice.carservice.entity.Car;
import com.microservice.carservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public Car getCar(Integer id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getCarsByUserId(Integer userId){
        return carRepository.findByUserId(userId);
    }
}

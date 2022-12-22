package com.microservice.userservice.service;

import com.microservice.userservice.config.RestTemplateConfig;
import com.microservice.userservice.entity.User;
import com.microservice.userservice.model.Bike;
import com.microservice.userservice.model.Car;
import com.microservice.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<Car> getCars(Integer userId){
        List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/all/" + userId, List.class);
        return cars;
    }

    public List<Bike> getBikes(Integer userId){
        List<Bike> bikes = restTemplate.getForObject("http://localhost:8003/bike/all/" + userId, List.class);
        return bikes;
    }
}

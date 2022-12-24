package com.microservice.userservice.service;


import com.microservice.userservice.entity.User;
import com.microservice.userservice.feignclient.BikeFeignClient;
import com.microservice.userservice.feignclient.CarFeignClient;
import com.microservice.userservice.model.Bike;
import com.microservice.userservice.model.Car;
import com.microservice.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CarFeignClient carFeignClient;

    @Autowired
    BikeFeignClient bikeFeignClient;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<Car> getCars(Integer userId) {
        List<Car> cars = carFeignClient.getCars(userId);
        return cars;
    }

    public List<Bike> getBikes(Integer userId) {
        List<Bike> bikes = bikeFeignClient.getBikes(userId);
        return bikes;
    }

    public HashMap<String, Object> getCarsAndBikes(Integer userId){
        User user = userRepository.getById(userId);
        HashMap<String, Object> vehicles = new HashMap<>();

        List<Car> cars = carFeignClient.getCars(userId);
        if(cars == null){
            vehicles.put("Cars", "User " + user.getName() + " has no cars");
        }else {
            vehicles.put("Cars", cars);
        }

        List<Bike> bikes = bikeFeignClient.getBikes(userId);
        if(bikes == null){
            vehicles.put("Bikes", "User " + user.getName() + " has no bikes");
        }else {
            vehicles.put("Bikes", bikes);
        }
        return vehicles;
    }

    public Car saveCar(Integer userId, Car car) {
        car.setUserId(userId);
        return carFeignClient.newCar(car);
    }

    public Bike saveBike(Integer userId, Bike bike) {
        bike.setUserId(userId);
        return bikeFeignClient.newBike(bike);
    }
}

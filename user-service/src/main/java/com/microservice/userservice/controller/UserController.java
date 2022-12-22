package com.microservice.userservice.controller;

import com.microservice.userservice.entity.User;
import com.microservice.userservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}

package com.epam.homework_5.controller;

import com.epam.homework_5.entity.User;
import com.epam.homework_5.exception.UserNotFound;
import com.epam.homework_5.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("users")
    public List<User> all(){
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User one(@PathVariable Long id){
        return userRepository.findById(id).orElseThrow(UserNotFound::new);
    }
}

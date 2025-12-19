package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.SignupRequest;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Component
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    User user;

    public UserDto Login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()->new RuntimeException("unable to signup"));
        return new UserDto(user.getId(), user.getName(), user.getEmail());


    }


    public UserDto SignUp(SignupRequest request) {
//        Optional<User> userContainer = userRepository.findByEmail(request.getEmail());
//        if (userContainer.isPresent())
//        {
//            return null;
//
//        } else {
//            User user = new User(request.getName(), request.getEmail(), request.getPassword());
//            userRepository.save(user);
//            return new UserDto(user.getId(), user.getName(), user.getEmail());
      //  }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        User savedUser = userRepository.save(user);
        return new UserDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail());

    }
}

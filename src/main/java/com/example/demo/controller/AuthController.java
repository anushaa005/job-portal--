package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.SignupRequest;
import com.example.demo.service.UserService;
import com.example.demo.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class AuthController
{
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public UserDto login(@RequestBody LoginRequest request)
    {
       UserDto userDto= userService.Login(request.getEmail(), request.getPassword());
      return userDto;
    }

    @PostMapping("/signup")
    public UserDto SignUp(@RequestBody SignupRequest request)
    {
        UserDto userDto = userService.SignUp(request);
        return userDto;
    }

}

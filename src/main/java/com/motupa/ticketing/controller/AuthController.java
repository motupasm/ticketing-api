package com.motupa.ticketing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.motupa.ticketing.request.RegisterRequest;
import com.motupa.ticketing.response.UserResponse;
import com.motupa.ticketing.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping ("/api/auth")
@RequiredArgsConstructor 
public class AuthController {
public final UserService userService;

    @PostMapping ("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserResponse register(@Valid @RequestBody RegisterRequest request){
        return userService.register(request);
    }
}

package com.ecommerce.auth.controllers;

import com.ecommerce.auth.dtos.LoginRequest;
import com.ecommerce.auth.dtos.LoginResponse;
import com.ecommerce.auth.dtos.UserRequest;
import com.ecommerce.auth.dtos.UserResponse;
import com.ecommerce.auth.services.AuthService;
import com.ecommerce.auth.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid  LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequest request) {
        UserResponse response = userService.register(request);
        return ResponseEntity.ok(response);
    }
}

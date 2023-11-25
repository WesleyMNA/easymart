package com.ecommerce.auth.services;

import com.ecommerce.auth.dtos.UserRequest;
import com.ecommerce.auth.dtos.UserResponse;
import com.ecommerce.auth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    public UserResponse register(UserRequest request) {
        return null;
    }
}

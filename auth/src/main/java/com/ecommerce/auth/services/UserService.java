package com.ecommerce.auth.services;

import com.ecommerce.auth.dtos.UserRequest;
import com.ecommerce.auth.dtos.UserResponse;
import com.ecommerce.auth.exceptions.BadRequestException;
import com.ecommerce.auth.models.User;
import com.ecommerce.auth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserResponse register(UserRequest request) {
        validateEmail(request.email());
        var user = new User();
        BeanUtils.copyProperties(request, user);
        user.setPassword(encoder.encode(request.password()));
        repository.save(user);
        var response = new UserResponse();
        BeanUtils.copyProperties(user, response);
        return response;
    }

    private void validateEmail(String email) {
        Optional<User> optionalUser = repository.findByEmail(email);

        if (optionalUser.isPresent())
            throw new BadRequestException("email already exists");
    }
}

package com.ecommerce.auth.services;

import com.ecommerce.auth.dtos.LoginRequest;
import com.ecommerce.auth.dtos.LoginResponse;
import com.ecommerce.utils.exceptions.UnauthorizedException;
import com.ecommerce.auth.models.User;
import com.ecommerce.auth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository repository;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;

    public LoginResponse login(LoginRequest request) {
        User user = validateRequest(request);
        return jwtService.createToken(user);
    }

    private User validateRequest(LoginRequest request) {
        Optional<User> optional = repository.findByEmail(request.email());

        if (optional.isEmpty() || !encoder.matches(request.password(), optional.get().getPassword()))
            throw new UnauthorizedException("email or password invalid");

        return optional.get();
    }
}

package com.ecommerce.auth.dtos;

public record LoginRequest(
        String email,
        String password
) {
}

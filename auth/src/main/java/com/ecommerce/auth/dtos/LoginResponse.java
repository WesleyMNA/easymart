package com.ecommerce.auth.dtos;

public record LoginResponse(
        String jwt,
        String type
) {
}

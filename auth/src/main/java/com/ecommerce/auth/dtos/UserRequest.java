package com.ecommerce.auth.dtos;

import com.ecommerce.auth.validators.password.CheckPasswords;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@CheckPasswords(password = "password", confirmPassword = "confirmPassword")
public record UserRequest(
        @NotNull
        @NotBlank
        String name,
        @NotNull
        @NotBlank
        String email,
        @NotNull
        @NotBlank
        String password,
        @NotNull
        @NotBlank
        String confirmPassword
) {
}

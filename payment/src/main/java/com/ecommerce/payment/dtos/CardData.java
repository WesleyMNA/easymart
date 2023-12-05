package com.ecommerce.payment.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CardData(
        @NotNull
        @NotBlank
        String ownerName,
        @NotNull
        @NotBlank
        String number,
        @NotNull
        @NotBlank
        String secret,
        @NotNull
        Boolean save
) {
}

package com.ecommerce.payment.dtos;


import jakarta.validation.constraints.NotNull;

public record OrderData(
        @NotNull
        Long orderId,
        @NotNull
        Float total
) {
}

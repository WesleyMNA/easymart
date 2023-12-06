package com.ecommerce.cart.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class ProductRequest {

    @NotNull
    private UUID catalogId;
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @Min(1)
    private Float price;
    @NotNull
    private Integer quantity;
}

package com.ecommerce.cart.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {

    @NotNull
    private Long catalogId;
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @Min(1)
    private Float price;
    @NotNull
    private Integer quantity;
}

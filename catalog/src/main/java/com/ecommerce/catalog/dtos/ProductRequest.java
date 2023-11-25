package com.ecommerce.catalog.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {

    @NotNull
    @NotBlank
    private String title;
    @NotNull
    private Float price;
}

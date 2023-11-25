package com.ecommerce.catalog.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {

    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @Min(1)
    private Float price;
}

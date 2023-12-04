package com.ecommerce.order.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderRequest {

    @NotNull
    private UUID productId;
    @NotNull
    private Integer quantity;
    @NotNull
    private Float price;
}

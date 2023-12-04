package com.ecommerce.order.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderProductResponse {

    private Long id;
    private Float price;
    private Long quantity;
    private UUID productId;
}

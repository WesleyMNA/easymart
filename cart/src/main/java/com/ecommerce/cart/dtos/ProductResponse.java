package com.ecommerce.cart.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductResponse {

    private Long id;
    private UUID catalogId;
    private String title;
    private Float price;
    private Integer quantity;
}

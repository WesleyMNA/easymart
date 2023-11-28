package com.ecommerce.cart.dtos;

import lombok.Data;

@Data
public class ProductResponse {

    private Long id;
    private String title;
    private Float price;
}

package com.ecommerce.catalog.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductResponse {

    private UUID id;
    private String title;
    private Float price;
}

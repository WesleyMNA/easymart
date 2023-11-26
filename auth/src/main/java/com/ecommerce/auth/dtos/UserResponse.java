package com.ecommerce.auth.dtos;

import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String name;
    private String email;
}

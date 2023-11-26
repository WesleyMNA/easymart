package com.ecommerce.auth.dtos;

import com.ecommerce.auth.validators.password.CheckPasswords;
import lombok.Data;

@Data
@CheckPasswords(password = "password", confirmPassword = "confirmPassword")
public class UserRequest {

    private String name;
    private String email;
    private String password;
    private String confirmPassword;
}

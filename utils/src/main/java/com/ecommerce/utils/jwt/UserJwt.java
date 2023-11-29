package com.ecommerce.utils.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Map;

@Data
@NoArgsConstructor
public class UserJwt {

    private Long id;
    private String name;
    private String email;

    public UserJwt(Jwt jwt) {
        Map<?, ?> context = (Map<?, ?>) jwt.getClaims().get("context");
        this.id = (Long) context.get("id");
        this.name = (String) context.get("name");
        this.email = (String) context.get("email");
    }
}

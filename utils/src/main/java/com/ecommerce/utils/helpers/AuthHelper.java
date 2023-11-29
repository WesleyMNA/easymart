package com.ecommerce.utils.helpers;

import com.ecommerce.utils.jwt.UserJwt;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public final class AuthHelper {

    public boolean hasAuthentication() {
        return getAuthentication() != null;
    }

    public UserJwt getCurrentUser() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();
        return new UserJwt(jwt);
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication();
    }
}

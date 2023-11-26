package com.ecommerce.utils.jwt;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;

@Getter
@Setter
@ToString
public class JwtAuthentication extends JwtAuthenticationToken {

    private final UserJwt user;

    public JwtAuthentication(Collection<? extends GrantedAuthority> authorities,
                             Jwt jwt,
                             UserJwt user) {
        super(jwt, authorities);
        this.user = user;
    }
}

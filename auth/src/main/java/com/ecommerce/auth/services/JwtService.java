package com.ecommerce.auth.services;


import com.ecommerce.auth.dtos.LoginResponse;
import com.ecommerce.auth.dtos.UserJwt;
import com.ecommerce.auth.models.User;
import com.ecommerce.auth.properties.JwtProperty;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;


@AllArgsConstructor
@Service
public final class JwtService {

    private final JwtEncoder encoder;
    private final JwtProperty property;

    public LoginResponse createToken(User user) {
        Instant now = Instant.now();
        Instant expiration = now.plus(property.lifetime(), ChronoUnit.MINUTES);
        var userJwt = new UserJwt();
        BeanUtils.copyProperties(user, userJwt);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("Task Manager API")
                .issuedAt(now)
                .expiresAt(expiration)
                .subject(String.valueOf(user.getId()))
                .claim("context", userJwt)
                .build();
        String jwt = encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new LoginResponse(jwt, "bearer");
    }
}

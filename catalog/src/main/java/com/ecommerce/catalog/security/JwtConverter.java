package com.ecommerce.catalog.security;

import com.ecommerce.auth.dtos.UserJwt;
import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class JwtConverter implements Converter<Jwt, JwtAuthentication> {

    @Override
    public JwtAuthentication convert(Jwt jwt) {
        LinkedTreeMap<Object, Object> context = jwt.getClaim("context");
        var user = new UserJwt();
        BeanUtils.copyProperties(context, user);
        return new JwtAuthentication(List.of(Role.USER), jwt, user);
    }

    public enum Role implements GrantedAuthority {
        USER;

        @Override
        public String getAuthority() {
            return "ROLE_" + this.name();
        }

    }
}

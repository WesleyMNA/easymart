package com.ecommerce.order.security;

import com.ecommerce.utils.jwt.JwtConverter;
import com.ecommerce.utils.properties.JwtProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtProperty jwtProperty;
    private final JwtConverter converter;

    private final String[] SWAGGER_ENDPOINTS = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorizeConfig -> {
                            authorizeConfig.requestMatchers(SWAGGER_ENDPOINTS).permitAll();
                            authorizeConfig.anyRequest().authenticated();
                        }
                )
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(converter)))
                .build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder
                .withPublicKey(jwtProperty.publicKey())
                .build();
    }
}

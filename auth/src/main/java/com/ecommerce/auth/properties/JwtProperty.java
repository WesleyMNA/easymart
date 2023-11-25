package com.ecommerce.auth.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "easymart.auth.jwt")
public record JwtProperty(RSAPublicKey publicKey, RSAPrivateKey privateKey, Integer lifetime) {
}

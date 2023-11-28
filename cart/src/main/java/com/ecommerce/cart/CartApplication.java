package com.ecommerce.cart;

import com.ecommerce.utils.properties.JwtProperty;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties(value = {JwtProperty.class})
@ComponentScan("com.ecommerce.utils")
@SecurityScheme(type = SecuritySchemeType.APIKEY, name = "Authorization", in = SecuritySchemeIn.HEADER)
@OpenAPIDefinition(
		info = @Info(title = "Easymart cart API", version = "0.0.1"),
		security = {@SecurityRequirement(name = "Authorization")}
)
public class CartApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartApplication.class, args);
	}

}

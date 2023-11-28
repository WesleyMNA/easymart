package com.ecommerce.cart.controllers;

import com.ecommerce.cart.dtos.ProductRequest;
import com.ecommerce.cart.dtos.ProductResponse;
import com.ecommerce.cart.services.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class CartController {

    private final CartService service;

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> findAll(@ParameterObject Pageable pageable) {
        Page<ProductResponse> response = service.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> addProductToCart(@RequestBody @Valid ProductRequest request)
            throws JSONException {
        ProductResponse response = service.addProductToCart(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Long id) {
        service.removeProductFromCart(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}

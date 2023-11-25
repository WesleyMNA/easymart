package com.ecommerce.catalog.controllers;

import com.ecommerce.catalog.dtos.ProductResponse;
import com.ecommerce.catalog.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private final ProductService service;

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> findAll(@ParameterObject Pageable pageable) {
        Page<ProductResponse> response = service.findAll(pageable);
        return ResponseEntity.ok(response);
    }
}

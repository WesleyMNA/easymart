package com.ecommerce.catalog.controllers;

import com.ecommerce.catalog.dtos.ProductRequest;
import com.ecommerce.catalog.dtos.ProductResponse;
import com.ecommerce.catalog.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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

    @PostMapping
    private ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest request,
                                                   UriComponentsBuilder builder) {
        ProductResponse response = service.create(request);
        URI uri = builder.path("/v1/products/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity
                .created(uri)
                .body(response);
    }
}

package com.ecommerce.order.controllers;

import com.ecommerce.order.dtos.OrderRequest;
import com.ecommerce.order.dtos.OrderResponse;
import com.ecommerce.order.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class OrderController {

    private final OrderService service;

    @GetMapping
    public ResponseEntity<Page<OrderResponse>> findAll(@ParameterObject Pageable pageable) {
        Page<OrderResponse> response = service.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody @Valid List<OrderRequest> request,
                                                UriComponentsBuilder builder) {
        OrderResponse response = service.create(request);
        URI uri = builder.path("/v1/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity
                .created(uri)
                .body(response);
    }
}

package com.ecommerce.catalog.services;

import com.ecommerce.catalog.dtos.ProductResponse;
import com.ecommerce.catalog.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository repository;
    private final ModelMapper mapper;

    public Page<ProductResponse> findAll(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map((element) -> mapper.map(element, ProductResponse.class));
    }
}

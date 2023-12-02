package com.ecommerce.catalog.services;

import com.ecommerce.catalog.dtos.ProductRequest;
import com.ecommerce.catalog.dtos.ProductResponse;
import com.ecommerce.utils.exceptions.NotFoundException;
import com.ecommerce.catalog.models.Product;
import com.ecommerce.catalog.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    public ProductResponse create(ProductRequest request) {
        Product product = mapper.map(request, Product.class);
        repository.save(product);
        return mapper.map(product, ProductResponse.class);
    }

    public void update(UUID id, ProductRequest request) {
        Product product = validateId(id);
        product.setTitle(request.getTitle());
        product.setPrice(request.getPrice());
        repository.save(product);
    }

    public void delete(UUID id) {
        Product product = validateId(id);
        repository.delete(product);
    }

    private Product validateId(UUID id) {
        return repository
                .findById(id)
                .orElseThrow(NotFoundException::new);
    }


}

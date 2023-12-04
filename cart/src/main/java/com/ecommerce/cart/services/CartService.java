package com.ecommerce.cart.services;

import com.ecommerce.cart.dtos.ProductRequest;
import com.ecommerce.cart.dtos.ProductResponse;
import com.ecommerce.cart.models.Product;
import com.ecommerce.cart.repositories.ProductRepository;
import com.ecommerce.utils.exceptions.NotFoundException;
import com.ecommerce.utils.helpers.AuthHelper;
import com.ecommerce.utils.jwt.UserJwt;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartService {

    private final ProductRepository repository;
    private final ModelMapper mapper;
    private final AuthHelper authHelper;

    public Page<ProductResponse> findAll(Pageable pageable) {
        UserJwt user = authHelper.getCurrentUser();
        return repository
                .findByUserId(user.getId(), pageable)
                .map((element) -> mapper.map(element, ProductResponse.class));
    }

    public ProductResponse addProductToCart(ProductRequest request) {
        Product product = findProduct(request);
        return mapper.map(product, ProductResponse.class);
    }

    public void removeProductFromCart(Long id) {
        Product product = validateId(id);
        repository.delete(product);
    }

    private Product findProduct(ProductRequest request) {
        UserJwt user = authHelper.getCurrentUser();
        Optional<Product> optional = repository.findByUserIdAndCatalogId(user.getId(), request.getCatalogId());
        Product product;

        if (optional.isPresent()) {
            product = optional.get();
            product.setQuantity(request.getQuantity());
        } else
            product = new Product(user.getId(), request.getCatalogId(), request.getTitle(),
                    request.getPrice(), request.getQuantity());

        repository.save(product);
        return product;
    }

    private Product validateId(Long id) {
        return repository
                .findById(id)
                .orElseThrow(NotFoundException::new);
    }
}

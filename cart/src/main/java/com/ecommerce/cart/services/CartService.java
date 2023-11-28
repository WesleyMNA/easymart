package com.ecommerce.cart.services;

import com.ecommerce.cart.dtos.ProductRequest;
import com.ecommerce.cart.dtos.ProductResponse;
import com.ecommerce.cart.models.Product;
import com.ecommerce.cart.models.User;
import com.ecommerce.cart.repositories.ProductRepository;
import com.ecommerce.cart.repositories.UserRepository;
import com.ecommerce.utils.exceptions.NotFoundException;
import com.ecommerce.utils.helpers.AuthHelper;
import com.ecommerce.utils.jwt.UserJwt;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final AuthHelper authHelper;

    public Page<ProductResponse> findAll(Pageable pageable) {
        return productRepository
                .findAll(pageable)
                .map((element) -> mapper.map(element, ProductResponse.class));
    }

    public ProductResponse addProductToCart(ProductRequest request)
            throws JSONException {
        User user = getCurrentUser();
        Product product = findProduct(user, request);
        user.setProducts(product);
        userRepository.save(user);
        return mapper.map(product, ProductResponse.class);
    }

    public void removeProductFromCart(Long id) {
        Product product = validateId(id);
        productRepository.delete(product);
    }

    private User getCurrentUser()
            throws JSONException {
        UserJwt currentUser = authHelper.getCurrentUser();
        return userRepository
                .findByEmail(currentUser.getEmail())
                .orElseGet(() -> userRepository.save(new User(currentUser.getName(), currentUser.getEmail())));
    }

    private Product findProduct(User user, ProductRequest request) {
        Optional<Product> optional = productRepository.findByUserAndCatalogId(user, request.getCatalogId());
        Product product;

        if (optional.isPresent()) {
            product = optional.get();
            product.setQuantity(request.getQuantity());
        } else
            product = new Product(request.getCatalogId(), request.getTitle(),
                    request.getPrice(), request.getQuantity());

        productRepository.save(product);
        return product;
    }

    private Product validateId(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(NotFoundException::new);
    }
}

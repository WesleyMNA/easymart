package com.ecommerce.order.services;

import com.ecommerce.order.dtos.OrderRequest;
import com.ecommerce.order.dtos.OrderResponse;
import com.ecommerce.order.models.User;
import com.ecommerce.order.repositories.OrderRepository;
import com.ecommerce.order.repositories.UserRepository;
import com.ecommerce.utils.helpers.AuthHelper;
import com.ecommerce.utils.jwt.UserJwt;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository repository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final AuthHelper authHelper;

    public Page<OrderResponse> findAll(Pageable pageable) {
        User user = getCurrentUser();
        return repository
                .findByUser(user, pageable)
                .map((element) -> mapper.map(element, OrderResponse.class));
    }

    private User getCurrentUser() {
        UserJwt currentUser = authHelper.getCurrentUser();
        return userRepository
                .findByEmail(currentUser.getEmail())
                .orElseGet(() -> userRepository.save(new User(currentUser.getName(), currentUser.getEmail())));
    }

    public OrderResponse create(List<OrderRequest> request) {
        return null;
    }
}

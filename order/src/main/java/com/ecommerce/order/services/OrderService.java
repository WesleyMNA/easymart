package com.ecommerce.order.services;

import com.ecommerce.order.dtos.OrderRequest;
import com.ecommerce.order.dtos.OrderResponse;
import com.ecommerce.order.repositories.OrderRepository;
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
    private final ModelMapper mapper;
    private final AuthHelper authHelper;

    public Page<OrderResponse> findAll(Pageable pageable) {
        UserJwt user = authHelper.getCurrentUser();
        return repository
                .findByUserId(user.getId(), pageable)
                .map((element) -> mapper.map(element, OrderResponse.class));
    }

    public OrderResponse create(List<OrderRequest> request) {
        return null;
    }
}

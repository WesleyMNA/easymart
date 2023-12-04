package com.ecommerce.order.services;

import com.ecommerce.order.dtos.OrderRequest;
import com.ecommerce.order.dtos.OrderResponse;
import com.ecommerce.order.enumerations.OrderStatus;
import com.ecommerce.order.models.Order;
import com.ecommerce.order.models.OrderProduct;
import com.ecommerce.order.models.Product;
import com.ecommerce.order.repositories.OrderProductRepository;
import com.ecommerce.order.repositories.OrderRepository;
import com.ecommerce.order.repositories.ProductRepository;
import com.ecommerce.utils.exceptions.BadRequestException;
import com.ecommerce.utils.exceptions.NotFoundException;
import com.ecommerce.utils.helpers.AuthHelper;
import com.ecommerce.utils.jwt.UserJwt;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;
    private final ModelMapper mapper;
    private final AuthHelper authHelper;

    public Page<OrderResponse> findAll(Pageable pageable) {
        UserJwt user = authHelper.getCurrentUser();
        return orderRepository
                .findByUserId(user.getId(), pageable)
                .map((element) -> mapper.map(element, OrderResponse.class));
    }

    public OrderResponse create(List<OrderRequest> request) {
        UserJwt user = authHelper.getCurrentUser();
        var order = new Order(user.getId(), LocalDateTime.now(), OrderStatus.PROCESSING);
        var products = new ArrayList<OrderProduct>();
        AtomicReference<Float> total = new AtomicReference<>(0f);
        request.forEach(orderRequest -> {
            Product product = productRepository
                    .findById(orderRequest.getProductId())
                    .orElseThrow(NotFoundException::new);

            if (product.getQuantity() == null ||
                    product.getQuantity() < orderRequest.getQuantity())
                throw new BadRequestException();

            var totalPrice = orderRequest.getQuantity() * orderRequest.getPrice();
            total.updateAndGet(v -> v + totalPrice);
            var orderProduct = new OrderProduct(orderRequest.getQuantity(), orderRequest.getPrice(),
                    order, product.getId());
            product.setQuantity(product.getQuantity() - orderProduct.getQuantity());
            productRepository.save(product);
            products.add(orderProduct);
        });
        order.setTotal(total.get());
        order.setProducts(products);
        orderRepository.save(order);
        return mapper.map(order, OrderResponse.class);
    }
}

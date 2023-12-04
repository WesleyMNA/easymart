package com.ecommerce.order.services;

import com.ecommerce.order.dtos.ProductAmqp;
import com.ecommerce.order.models.Product;
import com.ecommerce.order.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static com.ecommerce.order.amqp.ProductsAmqpConfig.QUEUE_NAME;

@Log4j2
@RequiredArgsConstructor
@Service
public class ProductsService {

    private final ProductRepository repository;

    @RabbitListener(queues = QUEUE_NAME)
    public void addProducts(ProductAmqp message) {
        var product = new Product(message);
        repository.save(product);
        log.info("New product added: %s".formatted(message));
    }
}

package com.ecommerce.order.services;

import com.ecommerce.order.dtos.PaymentAmqp;
import com.ecommerce.order.dtos.PaymentStatus;
import com.ecommerce.order.enumerations.OrderStatus;
import com.ecommerce.order.models.Order;
import com.ecommerce.order.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static com.ecommerce.order.amqp.AmqpConfig.PAYMENTS_QUEUE_NAME;

@Log4j2
@RequiredArgsConstructor
@Service
public class PaymentService {

    private final OrderRepository repository;

    @RabbitListener(queues = PAYMENTS_QUEUE_NAME)
    public void processOrder(PaymentAmqp message) {
        Order order = repository
                .findById(message.orderId())
                .orElseThrow(RuntimeException::new);

        if (message.status() == PaymentStatus.SUCCESS)
            order.setStatus(OrderStatus.PAYED);
        else
            order.setStatus(OrderStatus.DENIED);

        repository.save(order);
    }
}

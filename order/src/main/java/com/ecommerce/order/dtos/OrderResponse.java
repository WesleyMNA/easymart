package com.ecommerce.order.dtos;

import com.ecommerce.order.enumerations.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponse {

    private Long id;
    private Long userId;
    private LocalDateTime dateHour;
    private Float total;
    private OrderStatus status;
}

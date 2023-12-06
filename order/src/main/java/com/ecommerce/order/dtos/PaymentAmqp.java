package com.ecommerce.order.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentAmqp(
        UUID id,
        LocalDateTime createdAt,
        LocalDateTime processedAt,
        PaymentStatus status,
        Long orderId,
        Float total
) {
}

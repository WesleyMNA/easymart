package com.ecommerce.payment.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PaymentResponse {

    private UUID id;
    private LocalDateTime createdAt;
    private PaymentStatus status;
    private Long orderId;
    private Float total;

}

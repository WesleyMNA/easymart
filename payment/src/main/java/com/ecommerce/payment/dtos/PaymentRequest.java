package com.ecommerce.payment.dtos;

public record PaymentRequest(
        OrderData order,
        CardData card
) {

}

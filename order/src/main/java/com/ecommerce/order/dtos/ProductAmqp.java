package com.ecommerce.order.dtos;

import java.util.UUID;

public record ProductAmqp(
        UUID id,
        String title,
        Float price
) {

}

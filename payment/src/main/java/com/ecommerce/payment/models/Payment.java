package com.ecommerce.payment.models;

import com.ecommerce.payment.dtos.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "em_payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "processed_at")
    private LocalDateTime processedAt;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentStatus status;
    @Column(name = "order_id", nullable = false)
    private Long orderId;
    @Column(name = "total", nullable = false)
    private Float total;

    public Payment(LocalDateTime createdAt,
                   PaymentStatus status,
                   Long orderId,
                   Float total) {
        this.createdAt = createdAt;
        this.status = status;
        this.orderId = orderId;
        this.total = total;
    }
}

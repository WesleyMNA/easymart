package com.ecommerce.payment.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "em_card")
public class Card {

    @Id
    @Column(name = "number", nullable = false)
    private String number;
    @Column(name = "owner_name", nullable = false)
    private String ownerName;
    @Column(name = "secret", nullable = false)
    private String secret;
}

package com.ecommerce.payment.services;

import com.ecommerce.payment.components.PaymentProcessor;
import com.ecommerce.payment.dtos.CardData;
import com.ecommerce.payment.dtos.PaymentRequest;
import com.ecommerce.payment.dtos.PaymentResponse;
import com.ecommerce.payment.dtos.PaymentStatus;
import com.ecommerce.payment.models.Card;
import com.ecommerce.payment.models.Payment;
import com.ecommerce.payment.repositories.CardRepository;
import com.ecommerce.payment.repositories.PaymentRepository;
import com.ecommerce.utils.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentProcessor processor;
    private final CardRepository cardRepository;
    private final PaymentRepository paymentRepository;
    private final ModelMapper mapper;

    public PaymentResponse create(PaymentRequest request) {
        saveCardDataIfNeeded(request.card());
        var payment = new Payment(
                LocalDateTime.now(),
                PaymentStatus.WAITING,
                request.order().orderId(),
                request.order().total()
        );
        paymentRepository.save(payment);
        processor.processPayment(payment);
        return mapper.map(payment, PaymentResponse.class) ;
    }

    private void saveCardDataIfNeeded(CardData data) {
        if (!data.save())
            return;

        Boolean exists = cardRepository.existsByNumber(data.number());

        if (exists)
            return;

        var card = new Card(data.number(), data.ownerName(), data.secret());
        cardRepository.save(card);
    }
}

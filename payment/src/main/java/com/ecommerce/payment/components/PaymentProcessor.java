package com.ecommerce.payment.components;

import com.ecommerce.payment.dtos.PaymentStatus;
import com.ecommerce.payment.models.Payment;
import com.ecommerce.payment.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;

import static com.ecommerce.payment.amqp.PaymentsAmqpConfig.EXCHANGE_NAME;

@RequiredArgsConstructor
@Component
public class PaymentProcessor {

    private final PaymentRepository repository;
    private final RabbitTemplate template;

    @Async
    public void processPayment(Payment payment) {
        try {
            Random random = new Random();
            int oneSecond = 1000;
            int tenSeconds = oneSecond * 10;
            int fiveMinutes = oneSecond * 300;
            int sleepTime = random.ints(tenSeconds, fiveMinutes)
                    .findFirst()
                    .orElse(0);
            Thread.sleep(sleepTime);
            int processStatus = random.ints(1, 100)
                    .findFirst()
                    .orElse(0);

            if (processStatus <= 90)
                payment.setStatus(PaymentStatus.SUCCESS);
            else
                payment.setStatus(PaymentStatus.DENIED);

            repository.save(payment);
            template.convertAndSend(EXCHANGE_NAME, "process-payment", payment);
        } catch (InterruptedException ignored) {
        }
    }
}

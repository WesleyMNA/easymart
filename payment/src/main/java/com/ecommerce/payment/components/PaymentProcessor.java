package com.ecommerce.payment.components;

import com.ecommerce.payment.dtos.PaymentStatus;
import com.ecommerce.payment.models.Payment;
import com.ecommerce.payment.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import static com.ecommerce.payment.amqp.PaymentsAmqpConfig.EXCHANGE_NAME;

@Log4j2
@RequiredArgsConstructor
@Component
public class PaymentProcessor {

    private final PaymentRepository repository;
    private final RabbitTemplate template;

    private final Random random = new Random();

    @Async
    public void processPayment(Payment payment) {
        try {
            int oneSecond = 1000;
            int tenSeconds = oneSecond * 10;
            int fiveMinutes = oneSecond * 300;
            int sleepTime = random.ints(tenSeconds, fiveMinutes)
                    .findFirst()
                    .orElse(0);
            Thread.sleep(sleepTime);
            changeStatus(payment);
            payment.setProcessedAt(LocalDateTime.now());
            repository.save(payment);
            log.info("Payment processed: %s".formatted(payment));
            template.convertAndSend(EXCHANGE_NAME, "process-payment", payment);
        } catch (InterruptedException ignored) {
        }
    }

    private void changeStatus(Payment payment) {
        int processStatus = random.ints(1, 100)
                .findFirst()
                .orElse(0);

        if (processStatus <= 90)
            payment.setStatus(PaymentStatus.SUCCESS);
        else
            payment.setStatus(PaymentStatus.DENIED);
    }

    @EventListener
    public void processWaiting(ContextRefreshedEvent event) {
        List<Payment> payments = repository.findByStatus(PaymentStatus.WAITING);
        payments.forEach(payment -> {
            changeStatus(payment);
            payment.setProcessedAt(LocalDateTime.now());
            repository.save(payment);
            log.info("Payment processed: %s".formatted(payment));
            template.convertAndSend(EXCHANGE_NAME, "process-payment", payment);
        });
    }
}

package com.ecommerce.order.amqp;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!test")
@Configuration
public class AmqpConfig {

    public final static String EXCHANGE_NAME = "easymart.ex";
    public final static String QUEUE_NAME = "new-products.queue";

    @Bean
    public RabbitAdmin createRabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initializeAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public TopicExchange topicExchange() {
        return ExchangeBuilder
                .topicExchange(EXCHANGE_NAME)
                .build();
    }

    @Bean
    public Queue createQueue() {
        return QueueBuilder
                .durable(QUEUE_NAME)
                .build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(createQueue())
                .to(topicExchange())
                .with("new-products");
    }

    @Bean
    public Declarables declarePayments() {
        Queue queue = QueueBuilder
                .durable("payments")
                .build();
        Binding binding = BindingBuilder
                .bind(queue)
                .to(topicExchange())
                .with("process-payment");
        return new Declarables(
                queue,
                binding
        );
    }
}

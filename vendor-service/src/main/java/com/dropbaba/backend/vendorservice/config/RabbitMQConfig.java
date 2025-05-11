package com.dropbaba.backend.vendorservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // For vendor creation events
    @Value("${rabbitmq.vendor.exchange}")
    private String vendorCreatedExchange;

    @Value("${rabbitmq.vendor.queue}")
    private String vendorCreatedQueue;

    @Value("${rabbitmq.vendor.routing-key}")
    private String vendorCreatedRoutingKey;

    // For order placed events
    @Value("${rabbitmq.order-placed.exchange}")
    private String orderPlacedExchange;

    @Value("${rabbitmq.order-placed.queue}")
    private String orderPlacedQueue;

    @Value("${rabbitmq.order-placed.routing-key}")
    private String orderPlacedRoutingKey;

    // --- Vendor Created Config ---
    @Bean
    public TopicExchange vendorExchange() {
        return new TopicExchange(vendorCreatedExchange);
    }

    @Bean
    public Queue vendorQueue() {
        return new Queue(vendorCreatedQueue);
    }

    @Bean
    public Binding vendorBinding(Queue vendorQueue, TopicExchange vendorExchange) {
        return BindingBuilder.bind(vendorQueue).to(vendorExchange).with(vendorCreatedRoutingKey);
    }

    // --- Order Placed Config ---
    @Bean
    public TopicExchange orderPlacedExchange() {
        return new TopicExchange(orderPlacedExchange);
    }

    @Bean
    public Queue orderPlacedQueue() {
        return new Queue(orderPlacedQueue);
    }

    @Bean
    public Binding orderPlacedBinding(Queue orderPlacedQueue, TopicExchange orderPlacedExchange) {
        return BindingBuilder.bind(orderPlacedQueue).to(orderPlacedExchange).with(orderPlacedRoutingKey);
    }

    // Common
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}


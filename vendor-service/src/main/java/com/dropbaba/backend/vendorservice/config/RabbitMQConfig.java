package com.dropbaba.backend.vendorservice.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.vendor.exchange}")
    private String exchange;

    @Value("${rabbitmq.vendor.queue}")
    private String queue;

    @Value("${rabbitmq.vendor.routing-key}")
    private String routingKey;

    @Bean
    public TopicExchange vendorExchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Queue vendorQueue() {
        return new Queue(queue);
    }

    @Bean
    public Binding vendorBinding(Queue vendorQueue, TopicExchange vendorExchange) {
        return BindingBuilder.bind(vendorQueue).to(vendorExchange).with(routingKey);
    }
}

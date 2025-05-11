package com.dropbaba.backend.orderservice.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.vendor.exchange}")
    private String vendorExchange;

    @Value("${rabbitmq.vendor.routing-key}")
    private String vendorRoutingKey;

    public static final String VENDOR_EXCHANGE_NAME = "vendor.topic.exchange";
    public static final String VENDOR_ROUTING_KEY = "order.placed";

    @Bean
    public TopicExchange vendorTopicExchange() {
        return new TopicExchange(vendorExchange);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

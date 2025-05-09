package com.dropbaba.backend.auth.messaging;

import com.dropbaba.backend.auth.config.RabbitMQConfig;
import com.dropbaba.backend.auth.event.UserRegisteredEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public UserEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishUserRegisteredEvent(UserRegisteredEvent event) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.USER_REGISTERED_QUEUE, event);
    }
}

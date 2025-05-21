package com.dropbaba.backend.userservice.listener;

import com.dropbaba.backend.userservice.event.UserRegisteredEvent;
import com.dropbaba.backend.userservice.model.User;
import com.dropbaba.backend.userservice.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener {

    private final UserRepository userRepository;

    public UserEventListener(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RabbitListener(queues = "user.registered.queue")
    public void handleUserRegistered(UserRegisteredEvent event) {
        System.out.println("Received user registration event for: " + event.getEmail());

        if (userRepository.existsById(event.getUserId())) {
            System.out.println("User already exists, skipping: " + event.getUserId());
            return;
        }

        User user = new User();
        user.setFullName(event.getName());
        user.setEmail(event.getEmail());

        userRepository.save(user);
    }
}

package com.dropbaba.backend.auth.service;

import com.dropbaba.backend.auth.dto.AuthRequest;
import com.dropbaba.backend.auth.dto.AuthResponse;
import com.dropbaba.backend.auth.event.UserRegisteredEvent;
import com.dropbaba.backend.auth.messaging.UserEventPublisher;
import com.dropbaba.backend.auth.model.User;
import com.dropbaba.backend.auth.repository.UserRepository;
import com.dropbaba.backend.auth.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserEventPublisher userEventPublisher;

    public AuthService(UserRepository userRepository,
                       JwtUtil jwtUtil,
                       PasswordEncoder passwordEncoder,
                       UserEventPublisher userEventPublisher) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userEventPublisher = userEventPublisher;
    }

    public AuthResponse register(AuthRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName()) // add this if you store name in the model
                .role("USER")
                .build();

        userRepository.save(user);

        // 🔁 Publish UserRegisteredEvent
        UserRegisteredEvent event = new UserRegisteredEvent(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
        userEventPublisher.publishUserRegisteredEvent(event);

        return new AuthResponse(jwtUtil.generateToken(user));
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return new AuthResponse(jwtUtil.generateToken(user));
    }
}

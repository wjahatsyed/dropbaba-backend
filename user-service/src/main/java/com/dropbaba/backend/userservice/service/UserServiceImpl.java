package com.dropbaba.backend.userservice.service;

import com.dropbaba.backend.userservice.dto.UserRequest;
import com.dropbaba.backend.userservice.model.Address;
import com.dropbaba.backend.userservice.model.User;
import com.dropbaba.backend.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserRequest request) {
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());

        if (request.getAddresses() != null) {
            for (UserRequest.AddressRequest a : request.getAddresses()) {
                Address address = new Address();
                address.setStreet(a.getStreet());
                address.setCity(a.getCity());
                address.setZip(a.getZip());
                address.setUser(user); // link both sides
                user.getAddresses().add(address);
            }
        }

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}

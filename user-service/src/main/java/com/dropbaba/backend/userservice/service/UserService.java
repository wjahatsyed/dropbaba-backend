package com.dropbaba.backend.userservice.service;

import com.dropbaba.backend.userservice.dto.UserRequest;
import com.dropbaba.backend.userservice.model.User;

public interface UserService {
    User createUser(UserRequest request);
    User getUserById(Long id);
}

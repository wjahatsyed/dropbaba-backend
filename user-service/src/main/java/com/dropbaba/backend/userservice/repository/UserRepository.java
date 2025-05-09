package com.dropbaba.backend.userservice.repository;

import com.dropbaba.backend.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

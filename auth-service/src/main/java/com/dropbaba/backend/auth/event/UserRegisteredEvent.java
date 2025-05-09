package com.dropbaba.backend.auth.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisteredEvent implements Serializable {
    private Long userId;
    private String email;
    private String name;
}

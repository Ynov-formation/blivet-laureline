package com.example.security.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.security.entities.User}
 */
@Value
public class UserDto implements Serializable {
    String username;
    String password;
}
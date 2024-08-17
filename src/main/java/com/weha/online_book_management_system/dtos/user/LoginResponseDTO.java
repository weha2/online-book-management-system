package com.weha.online_book_management_system.dtos.user;

import com.weha.online_book_management_system.entity.UserEntity;

import java.util.Date;

public record LoginResponseDTO(
        String username,
        String email,
        String firstName,
        String lastName,
        String token,
        Date expiration
) {
    public LoginResponseDTO(UserEntity entity, String token, Date expiration) {
        this(
                entity.getUsername(),
                entity.getEmail(),
                entity.getFistName(),
                entity.getLastName(),
                token,
                expiration
        );
    }
}

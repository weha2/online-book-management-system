package com.weha.online_book_management_system.dtos.user;

public record RegisterRequestDTO(
        String username,
        String password,
        String email,
        String firstName,
        String lastName
) {
}

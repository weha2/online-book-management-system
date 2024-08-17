package com.weha.online_book_management_system.dtos.Review;

public record ReviewRequestDTO(
        Integer rating,
        String comment,
        Long bookId
) {
}

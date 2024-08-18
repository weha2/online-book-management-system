package com.weha.online_book_management_system.dtos.Review;

import com.weha.online_book_management_system.entity.ReviewEntity;

import java.time.LocalDateTime;

public record ResponseReviewDTO(
        Long id,
        Long bookId,
        Integer rating,
        String comment,
        UserReviewDTO user,
        LocalDateTime createdDate,
        LocalDateTime modifierDate
) {
    public ResponseReviewDTO(ReviewEntity entity) {
        this(
                entity.getId(),
                entity.getBook().getId(),
                entity.getRating(),
                entity.getComment(),
                new UserReviewDTO(
                        entity.getUser().getId(),
                        entity.getUser().getFistName(),
                        entity.getUser().getLastName()
                ),
                entity.getCreatedDate(),
                entity.getModifierDate()
        );
    }
}

record UserReviewDTO(
        Long userId,
        String fistName,
        String lastName
) {
}
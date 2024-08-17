package com.weha.online_book_management_system.dtos.Review;

import com.weha.online_book_management_system.entity.ReviewEntity;

import java.time.LocalDateTime;

public record ReviewResponseDTO(
        Long id,
        Long bookId,
        Integer rating,
        String comment,
        ReviewByUser user,
        LocalDateTime createdDate,
        LocalDateTime modifierDate
) {
    public ReviewResponseDTO(ReviewEntity entity) {
        this(
                entity.getId(),
                entity.getBook().getId(),
                entity.getRating(),
                entity.getComment(),
                new ReviewByUser(
                        entity.getUser().getId(),
                        entity.getUser().getFistName(),
                        entity.getUser().getLastName()
                ),
                entity.getCreatedDate(),
                entity.getModifierDate()
        );
    }
}

record ReviewByUser(
        Long userId,
        String fistName,
        String lastName
) {
}
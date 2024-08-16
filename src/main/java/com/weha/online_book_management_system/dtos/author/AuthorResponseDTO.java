package com.weha.online_book_management_system.dtos.author;

import com.weha.online_book_management_system.entity.AuthorEntity;

import java.time.LocalDateTime;

public record AuthorResponseDTO(
        Long id,
        String authorName,
        LocalDateTime createdDate,
        LocalDateTime modifierDate
) {
    public AuthorResponseDTO(AuthorEntity entity) {
        this(
                entity.getId(),
                entity.getAuthorName(),
                entity.getCreatedDate(),
                entity.getModifierDate()
        );
    }
}

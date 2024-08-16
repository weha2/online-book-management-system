package com.weha.online_book_management_system.dtos.publisher;

import com.weha.online_book_management_system.entity.PublisherEntity;

import java.time.LocalDateTime;

public record PublisherResponseDTO(
        Long id,
        String publisherName,
        LocalDateTime createdDate,
        LocalDateTime modifierDate
) {
    public PublisherResponseDTO(PublisherEntity entity) {
        this(
                entity.getId(),
                entity.getPublisherName(),
                entity.getCreatedDate(),
                entity.getModifierDate()
        );
    }
}

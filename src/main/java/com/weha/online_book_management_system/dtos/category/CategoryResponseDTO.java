package com.weha.online_book_management_system.dtos.category;

import com.weha.online_book_management_system.entity.CategoryEntity;

import java.time.LocalDateTime;

public record CategoryResponseDTO(
        Long id,
        String categoryName,
        LocalDateTime createdDate,
        LocalDateTime modifierDate
) {
    public CategoryResponseDTO(CategoryEntity entity) {
        this(
                entity.getId(),
                entity.getCategoryName(),
                entity.getCreatedDate(),
                entity.getModifierDate()
        );
    }
}

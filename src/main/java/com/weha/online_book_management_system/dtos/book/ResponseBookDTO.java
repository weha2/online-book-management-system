package com.weha.online_book_management_system.dtos.book;

import com.weha.online_book_management_system.dtos.author.ResponseAuthorDTO;
import com.weha.online_book_management_system.dtos.category.ResponseCategoryDTO;
import com.weha.online_book_management_system.dtos.publisher.ResponsePublisherDTO;
import com.weha.online_book_management_system.entity.BookEntity;

import java.time.LocalDateTime;
import java.util.List;

public record ResponseBookDTO(
        Long id,
        String title,
        List<ResponseAuthorDTO> authors,
        String isbn,
        LocalDateTime publicationDate,
        List<ResponsePublisherDTO> publishers,
        String description,
        Double price,
        Long stockQuantity,
        List<ResponseCategoryDTO> categories
) {
    public ResponseBookDTO(BookEntity entity) {
        this(
                entity.getId(),
                entity.getTitle(),
                entity
                        .getAuthors()
                        .stream().map(ResponseAuthorDTO::new)
                        .toList(),
                entity.getIsbn(),
                entity.getPublicationDate(),
                entity
                        .getPublishers()
                        .stream()
                        .map(ResponsePublisherDTO::new)
                        .toList(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getStockQuantity(),
                entity
                        .getCategories()
                        .stream()
                        .map(ResponseCategoryDTO::new)
                        .toList()
        );
    }
}

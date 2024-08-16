package com.weha.online_book_management_system.dtos.book;

import com.weha.online_book_management_system.dtos.author.AuthorResponseDTO;
import com.weha.online_book_management_system.dtos.category.CategoryResponseDTO;
import com.weha.online_book_management_system.dtos.publisher.PublisherResponseDTO;
import com.weha.online_book_management_system.entity.BookEntity;

import java.time.LocalDateTime;
import java.util.List;

public record BookResponseDTO(
        Long id,
        String title,
        List<AuthorResponseDTO> authors,
        String isbn,
        LocalDateTime publicationDate,
        List<PublisherResponseDTO> publishers,
        String description,
        Double price,
        Long stockQuantity,
        List<CategoryResponseDTO> categories
) {
    public BookResponseDTO(BookEntity entity) {
        this(
                entity.getId(),
                entity.getTitle(),
                entity
                        .getAuthors()
                        .stream().map(AuthorResponseDTO::new)
                        .toList(),
                entity.getIsbn(),
                entity.getPublicationDate(),
                entity
                        .getPublishers()
                        .stream()
                        .map(PublisherResponseDTO::new)
                        .toList(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getStockQuantity(),
                entity
                        .getCategories()
                        .stream()
                        .map(CategoryResponseDTO::new)
                        .toList()
        );
    }
}

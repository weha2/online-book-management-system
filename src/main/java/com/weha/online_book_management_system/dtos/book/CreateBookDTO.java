package com.weha.online_book_management_system.dtos.book;

import java.time.LocalDateTime;
import java.util.List;

public record CreateBookDTO(
        String title,
        String isbn,
        LocalDateTime publicationDate,
        String description,
        double price,
        long stockQuantity,
        String pictureBase64,
        List<Long> authors,
        List<Long> publishers,
        List<Long> categories
) {
}

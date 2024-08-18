package com.weha.online_book_management_system.dtos.order;

import com.weha.online_book_management_system.entity.OrderEntity;

import java.time.LocalDateTime;

public record ResponseOrderByUserDTO(
        Long userId,
        String firstName,
        String lastName,
        UserOrderDTO order
) {
    public ResponseOrderByUserDTO(OrderEntity entity) {
        this(
                entity.getUser().getId(),
                entity.getUser().getFistName(),
                entity.getUser().getLastName(),
                new UserOrderDTO(
                        entity.getId(),
                        entity.getBook().getId(),
                        entity.getBook().getTitle(),
                        entity.getStatus(),
                        entity.getQuantity(),
                        entity.getPriceAtOrder(),
                        entity.getQuantity() * entity.getPriceAtOrder(),
                        entity.getCreatedDate()
                )
        );
    }
}

record UserOrderDTO(
        Long id,
        Long bookId,
        String bookName,
        String orderStatus,
        Integer quantity,
        Double price,
        Double amount,
        LocalDateTime orderDate
) {
}
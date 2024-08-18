package com.weha.online_book_management_system.dtos.order;

public record CreateOrderDTO(
        Long bookId,
        Integer quantity
) {
}

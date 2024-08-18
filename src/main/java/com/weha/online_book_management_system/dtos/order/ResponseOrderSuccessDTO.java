package com.weha.online_book_management_system.dtos.order;

import java.time.LocalDateTime;

public record ResponseOrderSuccessDTO(
        Boolean isSuccess,
        String message,
        String orderStatus,
        LocalDateTime orderDate
) {
}

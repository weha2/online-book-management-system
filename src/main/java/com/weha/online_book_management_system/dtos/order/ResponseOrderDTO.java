package com.weha.online_book_management_system.dtos.order;

import com.weha.online_book_management_system.constans.OrderStatus;
import com.weha.online_book_management_system.entity.OrderEntity;

import java.time.LocalDateTime;

public record ResponseOrderDTO(
        Long orderId,
        Integer quantity,
        Double priceAtOrder,
        Double amount,
        String status,
        String paymentSlip,
        LocalDateTime orderDate,
        LocalDateTime paidDate,
        UserOderDTO user,
        BookOderDTO book
) {
    public ResponseOrderDTO(OrderEntity entity) {
        this(
                entity.getId(),
                entity.getQuantity(),
                entity.getPriceAtOrder(),
                entity.getQuantity() * entity.getPriceAtOrder(),
                entity.getStatus(),
                entity.getPaymentSlip(),
                entity.getCreatedDate(),
                entity.getStatus().equals(OrderStatus.CANCEL.name()) ? null : entity.getModifierDate(),
                new UserOderDTO(
                        entity.getUser().getId(),
                        entity.getUser().getFistName(),
                        entity.getUser().getLastName(),
                        entity.getUser().getEmail()
                ),
                new BookOderDTO(
                        entity.getBook().getId(),
                        entity.getBook().getTitle()
                )
        );
    }
}

record BookOderDTO(
        Long id,
        String title
) {
}

record UserOderDTO(
        Long id,
        String firstName,
        String lastName,
        String email
) {
}

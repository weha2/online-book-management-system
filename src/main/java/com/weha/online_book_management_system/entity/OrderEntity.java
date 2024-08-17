package com.weha.online_book_management_system.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private UserEntity user;

    @ManyToOne
    @JoinColumn(
            name = "book_id",
            nullable = false
    )
    private BookEntity book;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double priceAtOrder;

    @Column(nullable = false)
    private String status;

    @Column
    private String paymentSlip;

}

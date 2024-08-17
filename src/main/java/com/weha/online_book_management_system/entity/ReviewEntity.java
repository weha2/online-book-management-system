package com.weha.online_book_management_system.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "reviews")
public class ReviewEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(
            name = "book_id",
            nullable = false
    )
    private BookEntity book;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private UserEntity user;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false)
    private String comment;
}

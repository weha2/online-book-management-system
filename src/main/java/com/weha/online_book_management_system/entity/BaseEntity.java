package com.weha.online_book_management_system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    @Column(nullable = true)
    private LocalDateTime modifierDate;

    @Column(nullable = false)
    private final LocalDateTime createdDate = LocalDateTime.now();

}

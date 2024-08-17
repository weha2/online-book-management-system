package com.weha.online_book_management_system.repository;

import com.weha.online_book_management_system.entity.BookEntity;
import com.weha.online_book_management_system.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    Optional<List<ReviewEntity>> findByBook(BookEntity book);
}

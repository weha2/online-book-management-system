package com.weha.online_book_management_system.repository;

import com.weha.online_book_management_system.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    Page<BookEntity> findByTitleContaining(String title, Pageable pageable);
}

package com.weha.online_book_management_system.repository;

import com.weha.online_book_management_system.entity.BookEntity;
import com.weha.online_book_management_system.entity.OrderEntity;
import com.weha.online_book_management_system.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    Optional<List<OrderEntity>> findByUser(UserEntity user);

    Optional<List<OrderEntity>> findByBook(BookEntity book);
}

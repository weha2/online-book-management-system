package com.weha.online_book_management_system.repository;

import com.weha.online_book_management_system.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
}

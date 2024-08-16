package com.weha.online_book_management_system.repository;

import com.weha.online_book_management_system.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}

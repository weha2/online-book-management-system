package com.weha.online_book_management_system.repository;

import com.weha.online_book_management_system.entity.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {
}

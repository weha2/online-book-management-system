package com.weha.online_book_management_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "authors")
public class AuthorEntity extends BaseEntity {

    @Column(nullable = false)
    private String authorName;

    @ManyToMany(mappedBy = "authors")
    private List<BookEntity> books;

}

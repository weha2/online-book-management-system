package com.weha.online_book_management_system.services;

import ch.qos.logback.core.util.StringUtil;
import com.weha.online_book_management_system.dtos.book.BookRequestDTO;
import com.weha.online_book_management_system.dtos.book.BookResponseDTO;
import com.weha.online_book_management_system.entity.AuthorEntity;
import com.weha.online_book_management_system.entity.BookEntity;
import com.weha.online_book_management_system.entity.CategoryEntity;
import com.weha.online_book_management_system.entity.PublisherEntity;
import com.weha.online_book_management_system.repository.BookRepository;
import org.antlr.v4.runtime.misc.Triple;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final CategoryService categoryService;

    public BookService(BookRepository bookRepository,
                       AuthorService authorService,
                       PublisherService publisherService,
                       CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.categoryService = categoryService;
    }

    public List<BookResponseDTO> findBooks() {
        return bookRepository
                .findAll()
                .stream()
                .map(BookResponseDTO::new)
                .toList();
    }

    public BookResponseDTO findBookById(Long id) throws Exception {
        Optional<BookEntity> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new Exception("Not found book with id ".concat(String.valueOf(id)));
        }
        return new BookResponseDTO(book.get());
    }

    public BookResponseDTO createBook(BookRequestDTO req) throws Exception {
        validateRequest(req);
        Triple<List<AuthorEntity>, List<PublisherEntity>, List<CategoryEntity>> apc = getAuthorPublisherCategories(req);
        BookEntity book = new BookEntity();
        book.setTitle(req.title());
        book.setAuthors(apc.a);
        book.setIsbn(req.isbn());
        book.setPublicationDate(req.publicationDate());
        book.setPublishers(apc.b);
        book.setDescription(req.description());
        book.setPrice(req.price());
        book.setStockQuantity(req.stockQuantity());
        book.setCategories(apc.c);
        book.setModifierDate(LocalDateTime.now());
        BookEntity result = bookRepository.save(book);
        return new BookResponseDTO(result);
    }

    public BookResponseDTO updateBook(Long id, BookRequestDTO req) throws Exception {
        Optional<BookEntity> data = bookRepository.findById(id);
        if (data.isEmpty()) {
            throw new Exception("Not found book with id ".concat(String.valueOf(id)));
        }
        validateRequest(req);
        Triple<List<AuthorEntity>, List<PublisherEntity>, List<CategoryEntity>> apc = getAuthorPublisherCategories(req);
        BookEntity book = data.get();
        book.setTitle(req.title());
        book.setAuthors(apc.a);
        book.setIsbn(req.isbn());
        book.setPublicationDate(req.publicationDate());
        book.setPublishers(apc.b);
        book.setDescription(req.description());
        book.setPrice(req.price());
        book.setStockQuantity(req.stockQuantity());
        book.setCategories(apc.c);
        BookEntity result = bookRepository.save(book);
        return new BookResponseDTO(result);
    }

    public boolean deleteBook(Long id) throws Exception {
        if (!bookRepository.existsById(id)) {
            throw new Exception("Not found book with id ".concat(String.valueOf(id)));
        }
        bookRepository.deleteById(id);
        return true;
    }

    private Triple<List<AuthorEntity>, List<PublisherEntity>, List<CategoryEntity>> getAuthorPublisherCategories(
            BookRequestDTO req) throws Exception {
        List<AuthorEntity> authors = authorService.findAuthorsAllId(req.authors());
        List<PublisherEntity> publishers = publisherService.findPublisherByAllId(req.publishers());
        List<CategoryEntity> categories = categoryService.findCategoryByAllId(req.categories());
        return new Triple<>(authors, publishers, categories);
    }

    private void validateRequest(BookRequestDTO req) throws Exception {
        if (StringUtil.isNullOrEmpty(req.title())) {
            throw new Exception("Required title.");
        }

        if (req.authors().isEmpty()) {
            throw new Exception("Required author");
        }

        if (StringUtil.isNullOrEmpty(req.isbn())) {
            throw new Exception("Required ISBN.");
        }

        if (Objects.isNull(req.publicationDate())) {
            throw new Exception("Required publication date");
        }

        if (req.publishers().isEmpty()) {
            throw new Exception("Required publishers");
        }

        if (StringUtil.isNullOrEmpty(req.description())) {
            throw new Exception("Required description.");
        }

        if (req.price() < 0) {
            throw new Exception("Invalid price");
        }

        if (req.stockQuantity() < 0) {
            throw new Exception("Required stock quantity");
        }
    }
}

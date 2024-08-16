package com.weha.online_book_management_system.controllers;

import com.weha.online_book_management_system.dtos.DataState;
import com.weha.online_book_management_system.dtos.book.BookRequestDTO;
import com.weha.online_book_management_system.dtos.book.BookResponseDTO;
import com.weha.online_book_management_system.services.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book", description = "APIs for managing book.")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public ResponseEntity<DataState<List<BookResponseDTO>>> findAllBooks() {
        return ResponseEntity.ok(new DataState<>(bookService.findBooks()));
    }

    @GetMapping("{id}")
    public ResponseEntity<DataState<BookResponseDTO>> findBookById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new DataState<>(bookService.findBookById(id)));
    }

    @PostMapping("")
    public ResponseEntity<DataState<BookResponseDTO>> createBook(@RequestBody BookRequestDTO req) throws Exception {
        return ResponseEntity.ok(new DataState<>(bookService.createBook(req)));
    }

    @PutMapping("{id}")
    public ResponseEntity<DataState<BookResponseDTO>> updateBook(
            @PathVariable Long id,
            @RequestBody BookRequestDTO req) throws Exception {
        return ResponseEntity.ok(new DataState<>(bookService.updateBook(id, req)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DataState<Boolean>> deleteBook(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new DataState<>(bookService.deleteBook(id)));
    }
}

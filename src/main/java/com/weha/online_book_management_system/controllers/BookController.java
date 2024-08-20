package com.weha.online_book_management_system.controllers;

import com.weha.online_book_management_system.dtos.DataState;
import com.weha.online_book_management_system.dtos.DataStatePage;
import com.weha.online_book_management_system.dtos.book.CreateBookDTO;
import com.weha.online_book_management_system.dtos.book.ResponseBookDTO;
import com.weha.online_book_management_system.entity.BookEntity;
import com.weha.online_book_management_system.services.BookService;
import com.weha.online_book_management_system.utils.Tuple;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<DataStatePage<List<ResponseBookDTO>>> findAllBooks(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Tuple<Page<BookEntity>, List<ResponseBookDTO>> result = bookService.findBooks(title, page - 1, size);
        return ResponseEntity.ok(new DataStatePage<>(result.second(), result.fist()));
    }

    @GetMapping("{id}")
    public ResponseEntity<DataState<ResponseBookDTO>> findBookById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new DataState<>(bookService.findBookById(id)));
    }

    @PostMapping("")
    public ResponseEntity<DataState<ResponseBookDTO>> createBook(
            @RequestBody CreateBookDTO req
    ) throws Exception {
        return ResponseEntity.ok(new DataState<>(bookService.createBook(req)));
    }

    @PutMapping("{id}")
    public ResponseEntity<DataState<ResponseBookDTO>> updateBook(
            @PathVariable Long id,
            @RequestBody CreateBookDTO req) throws Exception {
        return ResponseEntity.ok(new DataState<>(bookService.updateBook(id, req)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DataState<Boolean>> deleteBook(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new DataState<>(bookService.deleteBook(id)));
    }
}

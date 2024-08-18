package com.weha.online_book_management_system.controllers;

import com.weha.online_book_management_system.dtos.DataState;
import com.weha.online_book_management_system.dtos.author.CreateAuthorDTO;
import com.weha.online_book_management_system.dtos.author.ResponseAuthorDTO;
import com.weha.online_book_management_system.services.AuthorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Author", description = "APIs for managing author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("")
    public ResponseEntity<DataState<List<ResponseAuthorDTO>>> findAllAuthors() {
        return ResponseEntity.ok(new DataState<>(authorService.findAllAuthors()));
    }

    @GetMapping("{id}")
    public ResponseEntity<DataState<ResponseAuthorDTO>> findAuthorById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new DataState<>(authorService.findAuthorById(id)));
    }

    @PostMapping("")
    public ResponseEntity<DataState<ResponseAuthorDTO>> createAuthor(@RequestBody CreateAuthorDTO req) {
        return ResponseEntity.ok(new DataState<>(authorService.createAuthor(req)));
    }

    @PutMapping("{id}")
    public ResponseEntity<DataState<ResponseAuthorDTO>> updateAuthor(
            @PathVariable Long id,
            @RequestBody CreateAuthorDTO req) throws Exception {
        return ResponseEntity.ok(new DataState<>(authorService.updateAuthor(id, req)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DataState<Boolean>> deleteAuthor(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new DataState<>(authorService.deleteAuthor(id)));
    }
}

package com.weha.online_book_management_system.controllers;

import com.weha.online_book_management_system.dtos.DataState;
import com.weha.online_book_management_system.dtos.category.CategoryRequestDTO;
import com.weha.online_book_management_system.dtos.category.CategoryResponseDTO;
import com.weha.online_book_management_system.services.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Category", description = "APIs for managing category.")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public ResponseEntity<DataState<List<CategoryResponseDTO>>> findAllCategories() {
        return ResponseEntity.ok(new DataState<>(categoryService.findAllCategory()));
    }

    @GetMapping("{id}")
    public ResponseEntity<DataState<CategoryResponseDTO>> findCategoryById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new DataState<>(categoryService.findCategoryById(id)));
    }

    @PostMapping("")
    public ResponseEntity<DataState<CategoryResponseDTO>> createCategory(@RequestBody CategoryRequestDTO req) {
        return ResponseEntity.ok(new DataState<>(categoryService.createCategory(req)));
    }

    @PutMapping("{id}")
    public ResponseEntity<DataState<CategoryResponseDTO>> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryRequestDTO req) throws Exception {
        return ResponseEntity.ok(new DataState<>(categoryService.updateCategory(id, req)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DataState<Boolean>> deleteCategory(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new DataState<>(categoryService.deleteCategory(id)));
    }
}

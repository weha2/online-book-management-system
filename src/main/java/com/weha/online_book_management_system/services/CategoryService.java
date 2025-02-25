package com.weha.online_book_management_system.services;

import com.weha.online_book_management_system.dtos.category.CreateCategoryDTO;
import com.weha.online_book_management_system.dtos.category.ResponseCategoryDTO;
import com.weha.online_book_management_system.entity.CategoryEntity;
import com.weha.online_book_management_system.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<ResponseCategoryDTO> findAllCategory() {
        return categoryRepository
                .findAll()
                .stream()
                .map(ResponseCategoryDTO::new)
                .toList();
    }

    public ResponseCategoryDTO findCategoryById(Long id) throws Exception {
        Optional<CategoryEntity> result = categoryRepository.findById(id);
        if (result.isEmpty()) {
            throw new Exception("Not found category with id ".concat(String.valueOf(id)));
        }
        return new ResponseCategoryDTO(result.get());
    }

    public List<CategoryEntity> findCategoryByAllId(List<Long> idx) throws Exception {
        List<CategoryEntity> categories = categoryRepository.findAllById(idx);
        if (categories.isEmpty()) {
            throw new Exception("Not found any category.");
        }
        return categories;
    }

    public ResponseCategoryDTO createCategory(CreateCategoryDTO req) {
        CategoryEntity category = new CategoryEntity();
        category.setCategoryName(req.categoryName());
        CategoryEntity result = categoryRepository.save(category);
        return new ResponseCategoryDTO(result);
    }

    public ResponseCategoryDTO updateCategory(Long id, CreateCategoryDTO req) throws Exception {
        Optional<CategoryEntity> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new Exception("Not found category with id ".concat(String.valueOf(id)));
        }
        CategoryEntity data = category.get();
        data.setCategoryName(req.categoryName());
        data.setModifierDate(LocalDateTime.now());
        CategoryEntity result = categoryRepository.save(data);
        return new ResponseCategoryDTO(result);
    }

    public boolean deleteCategory(Long id) throws Exception {
        if (!categoryRepository.existsById(id)) {
            throw new Exception("Not found category with id ".concat(String.valueOf(id)));
        }
        categoryRepository.deleteById(id);
        return true;
    }
}

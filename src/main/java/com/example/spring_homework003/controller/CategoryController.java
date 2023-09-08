package com.example.spring_homework003.controller;

import com.example.spring_homework003.model.entity.Category;
import com.example.spring_homework003.model.request.CategoryRequest;
import com.example.spring_homework003.model.response.CategoryResponse;
import com.example.spring_homework003.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories ")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all categories")
    public ResponseEntity<CategoryResponse<List<Category>>> getAllCategory() {

        CategoryResponse<List<Category>> response = CategoryResponse.<List<Category>>builder()
                .message("Get categories successfully")
                .payload(categoryService.getAllCategory())
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .httpStatus(HttpStatus.OK)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get categories by id successfully")
    public ResponseEntity<CategoryResponse<Category>> getCategoryById(@PathVariable("id") Integer categoryId) {

        CategoryResponse<Category> response = CategoryResponse.<Category>builder()
                .message("Get category by id successfully")
                .payload(categoryService.getCategoryById(categoryId))
                .httpStatus(HttpStatus.OK)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete category by id")
    public ResponseEntity<CategoryResponse<?>> deleteCategoriesById(@PathVariable("id") Integer categoriesId) {
        CategoryResponse<Category> response = null;
        if (categoryService.deleteCategoryById(categoriesId) == true) {
            response = CategoryResponse.<Category>builder()
                    .message("Delete category successfully")
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(response);
        }
        return null;
    }

    @PostMapping("/add")
    @Operation(summary = "Add new category")
    public ResponseEntity<CategoryResponse<Category>> addNewCategory(@RequestBody CategoryRequest categoryRequest) {

        Integer storeCategoryId = categoryService.addNewCategory(categoryRequest);

        if (storeCategoryId != null) {
            CategoryResponse<Category> response = CategoryResponse.<Category>builder()
                    .message("Add new category successfully")
                    .payload(categoryService.getCategoryById(storeCategoryId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(response);
        }
        return null;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update category successfully")
    public ResponseEntity<CategoryResponse<Category>> updateCategory(
            @RequestBody CategoryRequest categoryRequest, @PathVariable("id") Integer categoryId) {
        CategoryResponse<Category> response = null;
        Integer idCategoryUpdate = categoryService.updateCategory(categoryRequest, categoryId);
        if (idCategoryUpdate != null) {
            response = CategoryResponse.<Category>builder()
                    .message("Update successfully")
                    .payload(categoryService.getCategoryById(idCategoryUpdate))
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .httpStatus(HttpStatus.OK)
                    .build();

        }
        return ResponseEntity.ok(response);

    }
}

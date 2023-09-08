package com.example.spring_homework003.service.ServiceImp;

import com.example.spring_homework003.exception.UserBadRequestException;
import com.example.spring_homework003.exception.UserNotFoundException;
import com.example.spring_homework003.model.entity.Category;
import com.example.spring_homework003.model.request.CategoryRequest;
import com.example.spring_homework003.repository.CategoryRepository;
import com.example.spring_homework003.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAllCategory();
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        if (categoryRepository.getCategoriesById(categoryId) == null) {
            throw new UserNotFoundException("Category id : " + categoryId + "not found...!");
        }
        return categoryRepository.getCategoriesById(categoryId);
    }

    @Override
    public boolean deleteCategoryById(Integer categoryId) {
        if (categoryRepository.getCategoriesById(categoryId) == null) {
            throw new UserNotFoundException("Category id : " + categoryId + "not found...!");

        }
        return categoryRepository.deleteCategoriesById(categoryId);
    }

    @Override
    public Integer addNewCategory(CategoryRequest categoryRequest) {
        if (categoryRequest.getName().isEmpty() || categoryRequest.getName().isBlank()) {
            throw new UserBadRequestException("Category Name cannot be null");
        }
        return categoryRepository.addNewCategory(categoryRequest);
    }


    @Override
    public Integer updateCategory(CategoryRequest categoryRequest, Integer categoryId) {

        Integer categoryUpdateId = categoryRepository.updateCategory(categoryRequest, categoryId);

        return (categoryUpdateId);
    }
}

package com.example.spring_homework003.repository;

import com.example.spring_homework003.model.entity.Category;
import com.example.spring_homework003.model.request.CategoryRequest;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryRepository {

    @Select("SELECT * FROM categories")
    List<Category> findAllCategory();

    @Select("SELECT * FROM categories WHERE categories_id = #{categoriesId}")
    Category getCategoriesById(Integer categoriesId);

    @Delete("DELETE FROM categories WHERE categories_id = #{categoriesId}")
    Boolean deleteCategoriesById(Integer categoriesId);

    @Select("INSERT INTO categories (categories_name) VALUES(#{request.name})" +
            "RETURNING categories_id")
    Integer addNewCategory(@Param("request") CategoryRequest categoryRequest);

    @Select("UPDATE categories " +
            "SET categories_name = #{request.name}  " +
            "WHERE categories_id = #{categoryId} " +
            "RETURNING categories_id")
    Integer updateCategory(@Param("request") CategoryRequest categoryRequest , Integer categoryId);
}

package com.example.spring_homework003.repository;

import com.example.spring_homework003.model.entity.Author;
import com.example.spring_homework003.model.request.AuthorRequest;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AuthorRepository {


    @Select("SELECT * FROM authors")
    List<Author> findAllAuthor();

    @Select("SELECT * FROM authors WHERE author_id = #{authorId}")
    Author getAuthorById(Integer authorId);


    @Delete("DELETE FROM authors WHERE author_id = #{authorId}")
    Boolean deleteAuthorById(Integer authorId);


    @Select("INSERT INTO authors (author_name, gender) VALUES(#{request.author_name}, #{request.gender}) " +
            "RETURNING author_id ")
    Integer addNewAuthor(@Param("request") AuthorRequest authorRequest);

    @Select("UPDATE authors " +
            "SET author_name = #{request.author_name}, " +
            "gender = #{request.gender} " +
            "WHERE author_id = #{authorId} " +
            "RETURNING author_id")
    Integer updateAuthor(@Param("request") AuthorRequest authorRequest , Integer authorId);
}
package com.example.spring_homework003.repository;

import com.example.spring_homework003.model.entity.Books;
import com.example.spring_homework003.model.entity.Category;
import com.example.spring_homework003.model.request.AuthorRequest;
import com.example.spring_homework003.model.request.BookRequest;
import com.example.spring_homework003.model.response.BookResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.ibatis.annotations.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Mapper
public interface BookRepository {
    @Select("select * from categories c inner join book_details bd on c.categories_id = bd.category_id where book_id=#{bookId};")
    List<Category> getAllCategoryById(Integer bookId);
    @Select("SELECT * FROM books")
    @Results(
            id = "bookMapper",
            value = {
                    @Result(property = "author", column = "author_id", one = @One(select = "com.example.spring_homework003.repository.AuthorRepository.getAuthorById")),
                    @Result(property = "category",column = "book_id", many = @Many(select ="getAllCategoryById" )),
                    @Result(property = "book_id", column = "book_id")
            }
    )

    List<Books> getAllBook();

    @Select("SELECT * FROM books WHERE book_id = #{bookId}")
    @ResultMap("bookMapper")
    Books getBookById(Integer bookId);

    @Delete("DELETE FROM books WHERE book_id = #{bookId}")
    Boolean deleteBookById(Integer bookId);

    @Select("INSERT INTO books (title, author_id) VALUES(#{request.title}, #{request.author_id}) " +
            "RETURNING author_id")
    Integer addNewBook(@Param("request") BookRequest bookRequest);

    @Select("INSERT INTO book_details (book_id category_id) VALUE(#{request.bookId}, #{request.categoryId}")
    Integer addCategory(@Param("request") Integer bookId , Integer categoryId);





}

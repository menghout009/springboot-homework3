package com.example.spring_homework003.service;
import com.example.spring_homework003.model.entity.Books;
import com.example.spring_homework003.model.request.BookRequest;
import com.example.spring_homework003.model.request.CategoryRequest;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface BookService {


    List<Books> getAllBook();

    Books getBookById(Integer bookId);

    boolean deleteBookById(Integer bookId);

    Integer addNewBook(BookRequest bookRequest);
}

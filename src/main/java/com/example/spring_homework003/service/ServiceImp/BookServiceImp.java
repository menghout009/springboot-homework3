package com.example.spring_homework003.service.ServiceImp;

import com.example.spring_homework003.exception.UserNotFoundException;
import com.example.spring_homework003.model.entity.Books;
import com.example.spring_homework003.model.request.BookRequest;
import com.example.spring_homework003.repository.BookRepository;
import com.example.spring_homework003.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImp implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Books> getAllBook() {
        return bookRepository.getAllBook();
    }

    @Override
    public Books getBookById(Integer bookId) {
        if (bookRepository.getBookById(bookId) == null) {
            throw new UserNotFoundException("Book id : " + bookId + "not found...!");

    }
        return bookRepository.getBookById(bookId);
    }
    @Override
    public boolean deleteBookById(Integer bookId) {
        return bookRepository.deleteBookById(bookId);
    }

    @Override
    public Integer addNewBook(BookRequest bookRequest) {
        Integer book_id =  bookRepository.addNewBook(bookRequest);
        return book_id;
    }


}

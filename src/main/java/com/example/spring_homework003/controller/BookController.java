package com.example.spring_homework003.controller;

import com.example.spring_homework003.model.entity.Books;
import com.example.spring_homework003.model.request.BookRequest;
import com.example.spring_homework003.model.response.BookResponse;
import com.example.spring_homework003.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/v1/book1")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all book")
    public ResponseEntity<List<Books>> getAllBook() {

        return ResponseEntity.ok(bookService.getAllBook());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by id")
    public ResponseEntity<Books> getAllBook(@PathVariable("id") Integer bookId) {

        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    @DeleteMapping("/{book_id}")
    @Operation(summary = "delete book by id")
    public ResponseEntity<BookResponse<?>> deleteBookById(@PathVariable("book_id") Integer bookId) {
        BookResponse<?> response = null;
        if (bookService.deleteBookById(bookId) == true) {

            response = BookResponse.<Books>builder()
                    .message("Delete book successfully")
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add/book")
    @Operation(summary = "Add new book")
    public ResponseEntity<BookResponse<Books>> addNewBook (@RequestBody BookRequest bookRequest){
        Integer storeBookId = bookService.addNewBook(bookRequest);
//        Books books = bookService.getBookById(storeBookId);
        BookResponse<Books> response = BookResponse.<Books>builder()
                .message("Add new book successfully")
                .payload(bookService.getBookById(storeBookId))
                .httpStatus(HttpStatus.OK)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        return ResponseEntity.ok().body(response);
    }
}

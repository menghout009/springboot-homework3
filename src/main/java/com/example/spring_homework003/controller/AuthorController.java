package com.example.spring_homework003.controller;

import com.example.spring_homework003.model.entity.Author;
import com.example.spring_homework003.model.request.AuthorRequest;
import com.example.spring_homework003.model.response.AuthorResponse;
import com.example.spring_homework003.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all authors")
    public ResponseEntity<AuthorResponse<List<Author>>> getAllAuthor() {

        AuthorResponse<List<Author>> response = AuthorResponse.<List<Author>>builder()
                .message("Get author successfully")
                .payload(authorService.getAllAuthor())
                .httpStatus(HttpStatus.OK)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get author by id")
    public ResponseEntity<AuthorResponse<Author>> getAuthorById(@PathVariable("id") Integer authorId) {

        AuthorResponse<Author> response = null;

        if (authorService.getAuthorById(authorId) != null) {
            response = AuthorResponse.<Author>builder()
                    .message("Get author by id successfully")
                    .payload(authorService.getAuthorById(authorId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(response);
        } else {
            response = AuthorResponse.<Author>builder()
                    .message("Not found data ")
                    .payload(authorService.getAuthorById(authorId))
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{author_id}")
    @Operation(summary = "delete author by id")
    public ResponseEntity<AuthorResponse<?>> deleteAuthorById(@PathVariable("author_id") Integer authorId) {
        AuthorResponse<?> response = null;
        if (authorService.deleteAuthorById(authorId) == true) {

            response = AuthorResponse.<Author>builder()
                    .message("Delete author successfully")
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();

        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Add new author")
    public ResponseEntity<AuthorResponse<Author>> addNewAuthor(@RequestBody AuthorRequest authorRequest) {

        Integer storeAuthorId = authorService.addNewAuthor(authorRequest);

        if (storeAuthorId != null) {
            AuthorResponse<Author> response = AuthorResponse.<Author>builder()
                    .message("Add new author successfully")
                    .payload(authorService.getAuthorById(storeAuthorId))
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .httpStatus(HttpStatus.OK)
                    .build();
            return ResponseEntity.ok(response);
        }
        return null;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update author")
    public ResponseEntity<AuthorResponse<Author>> updateAuthor(
            @RequestBody AuthorRequest authorRequest, @PathVariable("id") Integer authorId

    ) {
        AuthorResponse<Author> response = null;
        Integer idAuthorUpdate = authorService.updateAuthor(authorRequest, authorId);
        if (idAuthorUpdate != null) {
            response = AuthorResponse.<Author>builder()
                    .message("Update author successfully")
                    .payload(authorService.getAuthorById(idAuthorUpdate))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();


        }
        return ResponseEntity.ok(response);
    }


}

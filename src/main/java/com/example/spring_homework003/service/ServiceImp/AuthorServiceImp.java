package com.example.spring_homework003.service.ServiceImp;

import com.example.spring_homework003.exception.UserBadRequestException;
import com.example.spring_homework003.exception.UserNotFoundException;
import com.example.spring_homework003.model.entity.Author;
import com.example.spring_homework003.model.request.AuthorRequest;
import com.example.spring_homework003.repository.AuthorRepository;
import com.example.spring_homework003.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AuthorServiceImp implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImp(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthor() {
        return authorRepository.findAllAuthor();
    }

    @Override
    public Author getAuthorById(Integer authorId) {
        if (authorRepository.getAuthorById(authorId) == null) {
            throw new UserNotFoundException("Author id : " + authorId + "not found...!");
        }
        return authorRepository.getAuthorById(authorId);
    }

    @Override
    public boolean deleteAuthorById(Integer authorId) {
        if (authorRepository.getAuthorById(authorId) == null) {
            throw new UserNotFoundException("Author id : " + authorId + "not found...!");
        }
        return authorRepository.deleteAuthorById(authorId);
    }


    @Override
    public Integer addNewAuthor(AuthorRequest authorRequest) {
        if (authorRequest.getAuthor_name().isEmpty() || authorRequest.getAuthor_name().isBlank() &&
                authorRequest.getGender().isEmpty() || authorRequest.getGender().isBlank()) {
            throw new UserBadRequestException("Author Add " + authorRequest + " empty");
        }
        Integer authorId = authorRepository.addNewAuthor(authorRequest);
        return authorId;
    }

    @Override
    public Integer updateAuthor(AuthorRequest authorRequest, Integer authorId) {
        if (authorRepository.getAuthorById(authorId) == null) {
            throw new UserNotFoundException("Author Id : " + authorId + " Not Found...!");
        } else if (authorRequest.getAuthor_name().isEmpty() || authorRequest.getAuthor_name().isBlank() &&
                authorRequest.getGender().isEmpty() || authorRequest.getGender().isBlank()) {
            throw new UserBadRequestException("Author Name and Gender cannot null");
        }
        Integer authorIdUpdate = authorRepository.updateAuthor(authorRequest, authorId);
        return authorId;
    }


}

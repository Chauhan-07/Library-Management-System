package com.LibraryManagementSystem.LMS.project.Service.Author;

import com.LibraryManagementSystem.LMS.project.Entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Author create(Author author);
    List<Author> getAllAuthors();

    Optional<Author> getAuthorById(int id);

    Author updateAuthor(int id,Author authordetails);

    void delete(int id);
}


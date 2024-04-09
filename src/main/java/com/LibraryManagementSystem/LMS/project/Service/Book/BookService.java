package com.LibraryManagementSystem.LMS.project.Service.Book;

import com.LibraryManagementSystem.LMS.project.Entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book create (Book book);
    List<Book> getAllBooks();

    Optional<Book> getBookById(int id);
    Optional<List<Book>> getBookByAuthorId(int authorId);

    Optional<List<Book>>  getBookByGenreId(int genreId);

    void addOrUpdateBook(Book book);

    String reserveBook(int bookId, int userId);

    int getAvailableQuantity();

    int getTotalBooks();
}


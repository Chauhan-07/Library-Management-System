package com.LibraryManagementSystem.LMS.project.Service.Book;

import com.LibraryManagementSystem.LMS.project.DAO.AuthorDao;
import com.LibraryManagementSystem.LMS.project.DAO.BookDao;
import com.LibraryManagementSystem.LMS.project.DAO.GenreDao;
import com.LibraryManagementSystem.LMS.project.DAO.UserRepo;
import com.LibraryManagementSystem.LMS.project.Entity.Author;
import com.LibraryManagementSystem.LMS.project.Entity.Book;
import com.LibraryManagementSystem.LMS.project.Entity.Genre;
import com.LibraryManagementSystem.LMS.project.Entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService{

    @Autowired
    private BookDao bookDao;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private GenreDao genreDao;



    @Autowired
    private UserRepo userRepo;

    @Autowired
    public BookServiceImpl(BookDao bookDao,AuthorDao authorDao,GenreDao genreDao) {
        this.bookDao = bookDao;
        this.authorDao=authorDao;
        this.genreDao=genreDao;
    }

    @Override
    public Book create(Book book) {

        book.setReservation(0);
        return bookDao.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

    @Override
    public Optional<Book> getBookById(int id) {
        return bookDao.findById(id);
    }

    @Override
    public Optional<List<Book>> getBookByAuthorId(int authorId) {
        Optional<Author> authorOptional = authorDao.findById(authorId);


        return authorOptional.map(Author::getBookList);
    }

    @Override
    public Optional<List<Book>> getBookByGenreId(int genreId) {
        Optional<Genre> genreOptional=genreDao.findById(genreId);

        return genreOptional.map(Genre::getBookList);
    }


    @Override
    public void addOrUpdateBook(Book book) {
        if (book == null || book.getAuthor() == null || book.getAuthor().getId() == 0) {
                throw new IllegalArgumentException("Book or author is null");
            }

            Book existingBook = bookDao.findByAuthorIdAndGenreIdAndTitle(
                    book.getAuthor().getId(),
                    book.getGenre().getId(),
                    book.getTitle());

            if (existingBook != null) {
                existingBook.setQuantity(existingBook.getQuantity() + book.getQuantity());
                bookDao.save(existingBook);
            } else {
                bookDao.save(book);
            }
        }


    @Override
    public String reserveBook(int bookId, int userId) {
        Optional<Book> bookOptional = bookDao.findById(bookId);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            if (book.getQuantity() == 0) {
                // Assuming UserDao is a repository for User and userDao.findById(userId) retrieves the user
                Optional<User> userOptional = userRepo.findById(userId);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    book.getReservedUsers().add(user);
                    bookDao.save(book);
                    book.setReservation(book.getReservation()+1);

                    return "Book is reserved for you. We will notify you when it becomes available.";
                } else {
                    return "User not found.";
                }
            } else {
                return "Book is available.";
            }
        } else {
            return "Book not found.";
        }
    }


    @Override
    public int getAvailableQuantity() {
        return bookDao.findAll().stream().mapToInt(Book::getQuantity).sum();
    }


    }



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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private GenreDao genreDao;



    @Autowired
    private UserRepo userRepo;

    @Autowired
    public BookServiceImpl(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
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
        Optional<Genre> genreOptional = genreDao.findById(genreId);

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
            existingBook.setStock(existingBook.getQuantity()); // set the value of stock to be the same as quantity
            bookDao.save(existingBook);
        } else {
            book.setStock(book.getQuantity()); // set the value of stock to be the same as quantity
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
                    book.setReservation(book.getReservation() + 1);

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

    @Override
    public int getTotalBooks() {
        List<Book> books = bookDao.findAll();
        int totalStock = 0;

        for (Book book : books) {
            Integer stock = book.getStock(); // Use wrapper Integer instead of primitive int
            if (Objects.nonNull(stock)) { // Check for null value
                totalStock += stock; // Add to total only if stock is not null
            }
        }

        return totalStock;
    }
}



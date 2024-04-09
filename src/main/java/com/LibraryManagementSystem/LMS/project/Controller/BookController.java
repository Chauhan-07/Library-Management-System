package com.LibraryManagementSystem.LMS.project.Controller;

import com.LibraryManagementSystem.LMS.project.Entity.Book;
import com.LibraryManagementSystem.LMS.project.Entity.ReturnBook;
import com.LibraryManagementSystem.LMS.project.Entity.transaction_book;
import com.LibraryManagementSystem.LMS.project.Service.Book.BookService;
import com.LibraryManagementSystem.LMS.project.Service.TransactionBook.TBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



    @RestController
    @CrossOrigin
    @RequestMapping("/book")
    public class BookController {

        @Autowired
        private BookService bookService;
        private final TBService tbService;

        int totalBooks=0;

        @Autowired
        public BookController(BookService bookService, TBService tbService) {
            this.bookService = bookService;
            this.tbService = tbService;
        }
        @GetMapping
        public ResponseEntity<List<Book>> getAllBook()
        {
            List<Book> b= bookService.getAllBooks();
            return new ResponseEntity<>(b, HttpStatus.OK);
        }

//        @PostMapping
//        public ResponseEntity<Book> createBook(@RequestBody Book book)
//        {
//            Book b= bookService.create(book);
//            return new ResponseEntity<>(b, HttpStatus.CREATED);
//        }
        @PostMapping("/addOrUpdate")
        public ResponseEntity<String> addOrUpdateBook(@RequestBody Book book) {

            bookService.addOrUpdateBook(book);
            int quantity = book.getQuantity();
            totalBooks=totalBooks+quantity;
            return ResponseEntity.ok("Book added or updated successfully");
        }
        @GetMapping("/totalBooks")
        public ResponseEntity<Integer> getTotalBooks(){
            return ResponseEntity.ok(totalBooks);
        }
        @GetMapping("/{id}")
        public Book getBookById(@PathVariable int id)
        {
            Optional<Book> book = bookService.getBookById(id);
            return book.orElse(null);
        }

        @GetMapping("/byAuthor/{authorId}")
        public List<Book> getBookByAuthorId(@PathVariable int authorId)
        {
            Optional<List<Book>> optionalBooks=bookService.getBookByAuthorId(authorId);
            return optionalBooks.orElse(null);
        }

        @GetMapping("/byGenre/{genreId}")
        public List<Book> getBookByGenreId(@PathVariable int genreId)
        {
            Optional<List<Book>> optionalBooks=bookService.getBookByGenreId(genreId);
            return optionalBooks.orElse(null);
        }

        @PostMapping("/return")
        public ResponseEntity<String> returnBook(@RequestBody ReturnBook returnBook) {
            transaction_book transactionBookId = returnBook.getTransactionBook_id();
            // Assuming this is how you get the transactionBookId
            Book bookId=transactionBookId.getBook_id();
            int id=bookId.getId();
            tbService.returnBook(id);

            return ResponseEntity.ok("Book returned successfully!");
        }

    }



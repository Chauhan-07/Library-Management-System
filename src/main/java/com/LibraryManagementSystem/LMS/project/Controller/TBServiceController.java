package com.LibraryManagementSystem.LMS.project.Controller;

import com.LibraryManagementSystem.LMS.project.Entity.*;
import com.LibraryManagementSystem.LMS.project.Service.Book.BookService;
import com.LibraryManagementSystem.LMS.project.Service.TransactionBook.TBService;
import com.LibraryManagementSystem.LMS.project.Service.User.UserServiceImpls;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/transactionDetails")
public class TBServiceController {
    private final TBService tbService;
    private final UserServiceImpls userService;
    private final BookService bookService;
    private Book book;

    public TBServiceController(TBService tbService, UserServiceImpls userService, BookService bookService) {
        this.tbService = tbService;
        this.userService = userService;
        this.bookService = bookService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<transaction_book> saveTransaction_book(@RequestBody transaction_book Transaction_book) {
        int id = Transaction_book.getBook_id().getId(); // Assuming you have an id field in the JSON request body

        tbService.issueBook(id);
        transaction_book newTransaction_book = tbService.saveTransaction_Book(Transaction_book);
        return ResponseEntity.ok(newTransaction_book);
    }

    @GetMapping
    public List<transaction_book> getAllTransactionBook() {
        return tbService.getAllTransaction_Book();
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<transaction_book> updateTransactionBook(@PathVariable int id, @RequestBody transaction_book transaction_book) {
//        transaction_book updatedTransactionBook = tbService.updateTransactionBook(id, transaction_book);
//        return ResponseEntity.ok(updatedTransactionBook);
//    }



}

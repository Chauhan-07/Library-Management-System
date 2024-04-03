package com.LibraryManagementSystem.LMS.project.Controller;

import com.LibraryManagementSystem.LMS.project.Entity.Book;
import com.LibraryManagementSystem.LMS.project.Entity.Card;
import com.LibraryManagementSystem.LMS.project.Entity.ReturnBook;
import com.LibraryManagementSystem.LMS.project.Entity.transaction_book;
import com.LibraryManagementSystem.LMS.project.Service.ReturnBook.ReturnBookService;
import com.LibraryManagementSystem.LMS.project.Service.TransactionBook.TBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ReturnBookController {


    private final ReturnBookService returnBookService;
    private final TBService tbService;


    @Autowired
    public ReturnBookController(ReturnBookService returnBookService, TBService tbService) {
        this.returnBookService = returnBookService;
        this.tbService = tbService;

    }

    @PostMapping("/return")
    public ResponseEntity<ReturnBook> returnBook(@RequestBody ReturnBook returnBook) {

        //int id = returnBook.getTransactionBook_id().getBook_id().getId();// Assuming you have an id field in the JSON request body
        transaction_book tid = returnBook.getTransactionBook_id();

                int id = tid.getId();
                tbService.returnBook(id);




        ReturnBook returnbook = returnBookService.save(returnBook);
        return new ResponseEntity<>(returnbook, HttpStatus.CREATED);
}
}

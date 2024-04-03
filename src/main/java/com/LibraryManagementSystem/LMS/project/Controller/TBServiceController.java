package com.LibraryManagementSystem.LMS.project.Controller;

import com.LibraryManagementSystem.LMS.project.Entity.transaction_book;
import com.LibraryManagementSystem.LMS.project.Service.TransactionBook.TBService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/transactionDetails")
public class TBServiceController {
    private final TBService tbService;

    public TBServiceController(TBService tbService) {
        this.tbService = tbService;
    }

    @PostMapping
    public ResponseEntity<transaction_book> saveTransaction_book(@RequestBody transaction_book transaction_book) {
        int id = transaction_book.getBook_id().getId(); // Assuming you have an id field in the JSON request body
        tbService.issueBook(id);
        transaction_book newTransaction_book = tbService.saveTransaction_Book(transaction_book);
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
